package io.chart.lognomy.external.korbank

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KorbankFeignTest {

    lateinit var korbankSearchClient: KorbankSearchClient

    @Autowired
    lateinit var korbankSearchService: KorbankSearchService

    @BeforeEach
    fun setup() : Unit {
        korbankSearchClient = Feign.builder()
                .client(OkHttpClient())
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .target(KorbankSearchClient::class.java, "http://ecos.bok.or.kr/api/StatisticSearch/RV1NS82MWHJGX93N28C2")
    }

    // 월별 검색
    @Test
    fun testGetKospiMM(): Unit {
//        val selectKospiData = korbankSearchClient.selectMonthlyKospi(
//                topCategory = "028Y015",
//                midCategory = "1070000",
//                dateGubun = "MM",
//                startDate = "202001",
//                endDate = "202012"
//        )

        val result = korbankSearchService.selectMonthlyKospi(startDate = "202001", endDate = "202012")
        println(result)
    }

    // 일별 검색
    @Test
    fun testKospiDD(): Unit {
//        val selectKospiData = korbankSearchClient.selectDailyKospi(
//                topCategory = "064Y001",
//                midCategory = "0001000",
//                dateGubun = "DD",
//                startDate = "20200101",
//                endDate = "20201231"
//        )

        val result = korbankSearchService.selectDailyKospi(
                startDate = "20200101",
                endDate = "20201231"
        )

        println(result)
    }
}