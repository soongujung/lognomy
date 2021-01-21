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
class BulkKospiTest {

//    @Qualifier("elasticsearchClient")
//    @Autowired
//    lateinit var highLevelClient: RestHighLevelClient

    @Qualifier("amazonEsClient")
    @Autowired
    lateinit var amazonEsClient: RestHighLevelClient

    @Autowired
    lateinit var korbankSearchService: KorbankSearchService

    @Test
    @DisplayName("Bulk KOSPI TEST")
    fun testBulkInsert() : Unit {

        val dailyData = korbankSearchService.selectDailyKospi(
                startDate = "20180101", endDate = "20180105"
        )

        val (list_total_count, dataList) = dailyData.statisticSearch

        val bulkRequest : BulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
//        bulkRequest.setRefreshPolicy("wait_for")

        dataList.forEach {
            // --1) builder 를 사용한 bulk 데이터 주입
//            val builder: XContentBuilder = XContentFactory.jsonBuilder();
//            builder.startObject();
//            {
//                builder.field("DATA_VALUE", it.dataValue)
//                builder.field("STAT_NAME", it.statDesc)
//                builder.field("ITEM_CODE1", it.midCategory)
//                builder.field("STAT_CODE", it.topCategory)
//                builder.field("ITEM_NAME1", it.dataName)
//                builder.field("TIME", it.dateTime)
//            }
//            builder.endObject();
//            val indexRequest = IndexRequest("kospi")
//                    .id("time")
//                    .source(builder)
//
//            bulkRequest.add(indexRequest)

            // --2) Map 을 활용한 bulk 데이터 주입
            val jsonMap = HashMap<String, Any>()
            jsonMap.put("DATA_VALUE", it.dataValue)
            jsonMap.put("STAT_NAME", it.statDesc)
            jsonMap.put("ITEM_CODE1", it.midCategory)
            jsonMap.put("STAT_CODE", it.topCategory)
            jsonMap.put("ITEM_NAME1", it.dataName)
            it.dateTime?.let { it1 -> jsonMap.put("TIME", it1) }

            val indexRequest = IndexRequest("kospi")
                    .id("time")
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = amazonEsClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

    @Test
    @DisplayName("Bulk KOSPI AMAZON ES TEST")
    fun testBulkInsertAmazonEs() : Unit {
        val dailyData = korbankSearchService.selectDailyKospi(
                startDate = "20180101", endDate = "20180105"
        )

        val (list_total_count, dataList) = dailyData.statisticSearch

        val bulkRequest : BulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
//        bulkRequest.setRefreshPolicy("wait_for")

        dataList.forEach {

            // --2) Map 을 활용한 bulk 데이터 주입
            val jsonMap = HashMap<String, Any>()
            jsonMap.put("DATA_VALUE", it.dataValue)
            jsonMap.put("STAT_NAME", it.statDesc)
            jsonMap.put("ITEM_CODE1", it.midCategory)
            jsonMap.put("STAT_CODE", it.topCategory)
            jsonMap.put("ITEM_NAME1", it.dataName)
            it.dateTime?.let { it1 -> jsonMap.put("TIME", it1) }

            val indexRequest = IndexRequest("kospi")
                    .id("time")
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = amazonEsClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }
}