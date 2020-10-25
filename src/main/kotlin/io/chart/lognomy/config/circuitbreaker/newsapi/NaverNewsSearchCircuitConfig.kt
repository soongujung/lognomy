package io.chart.lognomy.config.circuitbreaker.newsapi

//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
//import io.github.resilience4j.feign.FeignDecorators
//import io.github.resilience4j.feign.Resilience4jFeign
import feign.Feign
import feign.FeignException
import feign.Logger
import feign.Request
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import io.chart.lognomy.newsapi.naver.NaverNewsItemDto
import io.chart.lognomy.newsapi.naver.NaverNewsListDto
import io.chart.lognomy.newsapi.naver.NaverNewsSearchClient
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.feign.FeignDecorators
import io.github.resilience4j.feign.Resilience4jFeign
import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.Exception
import java.time.Duration

@Configuration
class NaverNewsSearchCircuitConfig (
        @Value("\${conn.naver.news.baseUrl}")
        private val baseUrl : String
){
//    feign client (단순 feign client) 설정
    @Bean
    fun naverNewsSearchClient() : NaverNewsSearchClient{
        return Feign.builder()
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .logger(Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(NaverNewsSearchClient::class.java, baseUrl)
    }

    fun circuitBreakerConfig() : CircuitBreakerConfig {
        val circuitBreakerConfig : CircuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50f)
                .slowCallRateThreshold(50f)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .permittedNumberOfCallsInHalfOpenState(3)
                .build()

        return circuitBreakerConfig
    }

    // CricuitBreakerRegistry 를 얻어오면서 circuitBreaker 의 생성과 획득을 동시에 하기
    // 개별 Feign Client 에 Resilience4j 제공 CircuitBreaker 를 입히기
    @Bean
//    fun naverNewsSearchCircuitBreaker(): CircuitBreaker {
    fun naverNewsSearchCircuitBreaker(): NaverNewsSearchClient {
        // 1) 커스텀 circuit breaker 설정
        // 참고) 커스텀 circuit breaker 를 설정하는 방식은 아래 링크에 자세히 설명되어 있다.
        // https://resilience4j.readme.io/docs/circuitbreaker
        val circuitBreakerConfig : CircuitBreakerConfig = circuitBreakerConfig()

        // 2) CircuitBreaker 인스턴스 생성 - CircuitBreakerRegistry를 이용한다.
        val naverNewsCircuitBreaker: CircuitBreaker = CircuitBreakerRegistry
                .of(circuitBreakerConfig)
                .circuitBreaker("naverNewsSearch", circuitBreakerConfig)

        // 단순 CircuitBreaker만 리턴할 것이라면 아래와 같이 return naverNewsCircuitBreaker 를 해주자.
        // return naverNewsCircuitBreaker

        // 3) RateLimiter
        // RateLimiter 의 default 설정
        // val rateLimiter: RateLimiter = RateLimiter.ofDefaults("naverNewsSearch")

        // RateLimiter 커스텀 설정
        // 참고) https://resilience4j.readme.io/docs/ratelimiter
        val naverNewsRateLimiter: RateLimiter = naverNewsSearchRateLimiter()

        // 4) FeignDecorators 객체 생성
        // FeignDecorators 는 circuitBreaker, rateLimiter와 함께 설정한다.

        // 참고)
        // FeignDecorators 는 io.github.openfeign 을 추가한다고 해서 사용가능하지 않다.
        // resilience4j 에서 배포하는 라이브러리이기 때분에 io.github.resilience4j 에서 제공하는
        // resilience4j-feign:1.6.1 을 사용해야 한다.
        val feignDecorators: FeignDecorators = FeignDecorators.builder()
                .withCircuitBreaker(naverNewsCircuitBreaker)
                .withRateLimiter(naverNewsRateLimiter)
                .build()


        // 5) Fallback 설정
        // https://resilience4j.readme.io/docs/feign#fallback
        // 구체적으로 필요한 다른 인자들을 모두 지정해주어야하는데, 코틀린의 특성상 null 체크에 대해 민감하다.
        // 구체적인 코드를 작성하는데에 시간이 조금 걸려서 Fallback 을 구현하는 코드는 CircuitBreaker 관련 설정을 정리하고 나서 제일 나중에 구현해보고 난 후에 정리하려고 한다.
//        val fallback : NaverNewsSearchClientFallback = NaverNewsSearchClientFallback(
//                FeignException.FeignClientException(-1, "Error", Request.create(Request.HttpMethod.GET, "", mapOf("")))
//        )


        // 6) Feign 클라이언트에 Resilience4j Circuit Breaker 적용
        // https://resilience4j.readme.io/docs/feign#fallback
        val client : NaverNewsSearchClient = Resilience4jFeign
                .builder(feignDecorators)
                .target(NaverNewsSearchClient::class.java, "http://naver.com")

        return client
    }

    // 3) RateLimiter
    // 참고자료 : https://resilience4j.readme.io/docs/ratelimiter
    fun naverNewsSearchRateLimiter(): RateLimiter {

        // ofDefaults를 사용하지 않고 커스텀 설정을 할 경우에 대한 예.
        val rateLimiterConfig: RateLimiterConfig = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1L))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ofMillis(25L))
                .build()

        val rateLimiter: RateLimiter = RateLimiterRegistry.of(rateLimiterConfig)
                .rateLimiter("naverNewsSearchRateLimiter", rateLimiterConfig)

        return rateLimiter
    }

//    TODO : loadbalancer 설정 추가 후 연동 필요
//    circuit breaker 설정 (추후 적용 예정 Loadbalancer 설정이 추가로 필요해서 일단 주석처리
//    @Bean
//    fun getNaverNewsSearchClient(circuitBreakerRegistry: CircuitBreakerRegistry): NaverNewsSearchClient {
//        val circuitBreaker = circuitBreakerRegistry.circuitBreaker("NAVER_NEWS_SEARCH_API")
//
//        val feignDecorator: FeignDecorators = FeignDecorators.builder()
//                .withCircuitBreaker(circuitBreaker)
//                .build()
//
//        return Resilience4jFeign.builder(feignDecorator)
//                .encoder(JacksonEncoder())
//                .decoder(JacksonDecoder())
//                .logLevel(Logger.Level.FULL)
//                .logger(Slf4jLogger(NaverNewsSearchClient::class.java))
//                .retryer(CircuitNonRetryer())
////                .errorDecoder(ErrorDecoder.Default())
////                .options(Request.Options(connectTimeout, readTimeout, TimeUnit.MILLISECONDS, false))
//                .target(NaverNewsSearchClient::class.java, baseUrl)
//    }

//    @Bean
//    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
//        return CircuitBreakerRegistry.of(getCircuitBreakerConfig())
//    }
//
//    @Bean
//    fun getCircuitBreakerConfig(): CircuitBreakerConfig {
//        val circuitConfig = CircuitBreakerConfig.custom()
//                .waitDurationInOpenState(Duration.ofMillis(300))
//                .build()
//
//        return circuitConfig
//    }
}
