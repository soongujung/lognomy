package io.chart.lognomy.trending.stockplus

import io.chart.lognomy.external.stockplus.StockPlusSearchService
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
class BulkStockPlusTest {

    @Qualifier("elasticsearchClient")
    @Autowired
    lateinit var highLevelClient: RestHighLevelClient

    @Autowired
    lateinit var stockPlusSearchService: StockPlusSearchService

    @Test
    @DisplayName("연도별 재무제표 정보")
    fun testBulkInsert_StockPlusFinancialYY() : Unit {
        val (quarterly, yearly) = stockPlusSearchService.selectFinancialStatements("KOREA-A000080")

        val bulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)

        val stockCode : String = "KOREA-A000080"

        yearly.forEach {
            val jsonMap = HashMap<String, Any?>()
            jsonMap.put("sales", it.sales)
            jsonMap.put("net_profit_margin_rate", it.netProfitMarginRate)
            jsonMap.put("owners_net_profit", it.ownersNetProfit)
            jsonMap.put("operating_profit", it.operatingProfit)
            jsonMap.put("operating_profit_margin_rate", it.operatingProfitMarginRate)
            jsonMap.put("is_connect", it.isConnect)
            jsonMap.put("pbr", it.pbr)
            jsonMap.put("per", it.per)
            jsonMap.put("roe", it.roe)
            jsonMap.put("stock_code", stockCode)
            jsonMap.put("base_date", it.baseDate)

            val requestId = stockCode + "/" + it.baseDate
            val indexRequest = IndexRequest("financial_yy")
//                    .id("stock_code")
                    .id(requestId)
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

    @Test
    @DisplayName("분기별 재무제표")
    fun testBulkInsert_StockPlusFinancialQuarterly() : Unit {
        val (quarterly, yearly) = stockPlusSearchService.selectFinancialStatements("KOREA-A000080")

        val bulkRequest = BulkRequest()
        bulkRequest.timeout("2m")
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)

        val stockCode : String = "KOREA-A000080"

        quarterly.forEach {
            val jsonMap = HashMap<String, Any?>()
            jsonMap.put("sales", it.sales)
            jsonMap.put("net_profit_margin_rate", it.netProfitMarginRate)
            jsonMap.put("owners_net_profit", it.ownersNetProfit)
            jsonMap.put("operating_profit", it.operatingProfit)
            jsonMap.put("operating_profit_margin_rate", it.operatingProfitMarginRate)
            jsonMap.put("is_connect", it.isConnect)
            jsonMap.put("pbr", it.pbr)
            jsonMap.put("per", it.per)
            jsonMap.put("roe", it.roe)
            jsonMap.put("stock_code", stockCode)
            jsonMap.put("base_date", it.baseDate)

            val requestId = stockCode + "/" + it.baseDate
            val indexRequest = IndexRequest("financial_quarterly")
//                    .id("stock_code")
                    .id(requestId)
                    .source(jsonMap)

            bulkRequest.add(indexRequest)
        }

        val bulkResponse = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
        println(bulkResponse)
    }

}