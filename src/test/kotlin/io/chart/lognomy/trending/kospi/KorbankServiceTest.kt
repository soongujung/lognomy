package io.chart.lognomy.trending.kospi


import io.chart.lognomy.external.korbank.KorbankSearchService
import org.elasticsearch.client.RestHighLevelClient
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

/**
 * 외부 API 동작 테스트 (한국은행 경제통계시스템 API)
 */
@SpringBootTest
class KorbankServiceTest {

    @Qualifier("elasticsearchClient")
    @Autowired
    lateinit var highLevelClient: RestHighLevelClient

    @Autowired
    lateinit var korbankSearchService: KorbankSearchService

    // TODO:: 월별 데이터인데 일자가 들어갈 경우 에러를 낸다.
    // 일 데이터가 들어왔을 경우에 대해 파라미터를 변경하는 로직이 필요하다.
    @Test
    @DisplayName("코스피 ")
    fun testSelectDailyKospi() : Unit {
        val monthlyData = korbankSearchService.selectMonthlyKospi(
                startDate = "201801", endDate = "201805"
        )

        val (list_total_count, dataList) = monthlyData.statisticSearch
        print(dataList)
    }

    @Test
    @DisplayName("금리 > 한국")
    fun testSelectMonthlyInterestRateKR(): Unit {
        val monthlyData = korbankSearchService.selectMonthlyInterestRateKR(
                startDate = "201801", endDate = "201805"
        )

        val (list_total_count, dataList) = monthlyData.statisticSearch
        print(dataList)
    }

    @Test
    @DisplayName("금리 > 미국")
    fun testSelectMonthlyInterestRateUS(): Unit {
        val monthlyData = korbankSearchService.selectMonthlyInterestRateUS(
                startDate = "201801", endDate = "201805"
        )

        val (list_total_count, dataList) = monthlyData.statisticSearch
        print(dataList)
    }

    @Test
    @DisplayName("환율 > 원달러")
    fun testExchangeRateWonDallar(): Unit {
        val monthlyData = korbankSearchService.selectMonthlyExchangeRateDollar(
                startDate = "201801", endDate = "201805"
        )

        val (list_total_count, dataList) = monthlyData.statisticSearch
        print(dataList)
    }
}