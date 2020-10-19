package io.chart.lognomy.config.circuitbreaker.newsapi

import feign.Feign
import feign.Logger
import feign.codec.ErrorDecoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import io.chart.lognomy.config.circuitbreaker.CircuitNonRetryer
import io.chart.lognomy.newsapi.naver.NaverNewsSearchClient
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
//import io.github.resilience4j.feign.FeignDecorators
//import io.github.resilience4j.feign.Resilience4jFeign
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
