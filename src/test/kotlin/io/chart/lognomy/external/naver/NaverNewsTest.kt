package io.chart.lognomy.external.naver

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import io.chart.lognomy.external.newsapi.naver.NaverNewsListDto
import io.chart.lognomy.external.newsapi.naver.NaverNewsSearchClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NaverNewsTest {

    lateinit var naverNewsClient: NaverNewsSearchClient

    @BeforeEach
    fun setup() : Unit {
        naverNewsClient = Feign.builder()
                .client(OkHttpClient())
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .logger(Slf4jLogger(NaverNewsSearchClient::class.java))
                .logLevel(Logger.Level.BASIC)
                .target(NaverNewsSearchClient::class.java, "https://openapi.naver.com");
    }

    @Test
    @DisplayName("naver news 검색 기능 테스트")
    fun testNaverNewsClient(): Unit{
        val fetchNewsList : NaverNewsListDto = naverNewsClient.fetchNewsList(
                query = "본원소득수지", display = 30, start = 1, sort = "date"
        )

        println(fetchNewsList.items)
//        println(fetchNewsList.display)
//        println(fetchNewsList.lastBuildDate)
//        println(fetchNewsList.toString())
    }
}