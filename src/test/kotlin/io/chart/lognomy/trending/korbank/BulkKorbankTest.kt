package io.chart.lognomy.trending.korbank

import io.chart.lognomy.external.korbank.KorbankSearchService
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.support.WriteRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BulkKorbankTest {

    @Qualifier("elasticsearchClient")
    @Autowired
    lateinit var highLevelClient: RestHighLevelClient

    @Autowired
    lateinit var korbankSearchService: KorbankSearchService

    @Test
    @DisplayName("환율 Bulk Insert")
    fun testBulkInsert_ExchangeRateWonDollar() : Unit{
        val monthlyData = korbankSearchService.selectMonthlyExchangeRateDollar("202001", "202005")
        val (list_total_count, dataList) = monthlyData.statisticSearch

        val bulkRequest : BulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)

        dataList.forEach {
            val jsonMap = HashMap<String, Any>()
            jsonMap.put("DATA_VALUE", it.dataValue)
            jsonMap.put("STAT_NAME", it.statDesc)
            jsonMap.put("ITEM_CODE1", it.midCategory)
            jsonMap.put("STAT_CODE", it.topCategory)
            jsonMap.put("ITEM_NAME1", it.dataName)
            it.dateTime?.let { it1 -> jsonMap.put("TIME", it1) }

            val indexRequest = IndexRequest("won_dollar")
                    .id("time")
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

    @Test
    @DisplayName("한국 금리 Bulk Insert")
    fun testBulkInsert_InterestRateKr() : Unit{
        val monthlyData = korbankSearchService.selectMonthlyInterestRateKR("202001", "202005")
        val (list_total_count, dataList) = monthlyData.statisticSearch

        val bulkRequest : BulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)

        dataList.forEach {
            val jsonMap = HashMap<String, Any>()
            jsonMap.put("DATA_VALUE", it.dataValue)
            jsonMap.put("STAT_NAME", it.statDesc)
            jsonMap.put("ITEM_CODE1", it.midCategory)
            jsonMap.put("STAT_CODE", it.topCategory)
            jsonMap.put("ITEM_NAME1", it.dataName)
            it.dateTime?.let { it1 -> jsonMap.put("TIME", it1) }

            val indexRequest = IndexRequest("interest_rate_kr")
                    .id("time")
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

    @Test
    @DisplayName("미국 금리 Bulk Insert")
    fun testBulkInsert_InterestRateUs() : Unit{
        val monthlyData = korbankSearchService.selectMonthlyInterestRateKR("202001", "202005")
        val (list_total_count, dataList) = monthlyData.statisticSearch

        val bulkRequest : BulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)

        dataList.forEach {
            val jsonMap = HashMap<String, Any>()
            jsonMap.put("DATA_VALUE", it.dataValue)
            jsonMap.put("STAT_NAME", it.statDesc)
            jsonMap.put("ITEM_CODE1", it.midCategory)
            jsonMap.put("STAT_CODE", it.topCategory)
            jsonMap.put("ITEM_NAME1", it.dataName)
            it.dateTime?.let { it1 -> jsonMap.put("TIME", it1) }

            val indexRequest = IndexRequest("interest_rate_us")
                    .id("time")
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

}