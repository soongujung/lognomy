package io.chart.lognomy.external.stockplus

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StockPlusTest {

    lateinit var stockPlusSearchClient: StockPlusSearchClient

    val stockPlusFinancialUrl : String = "https://stockplus.com/api/companies/KOREA-A000080/financial_statements.json"
    val stockPlusCompaniesUrl : String = "https://stockplus.com/api/companies/KOREA-A000080.json"

    @BeforeEach
    fun setUp() : Unit {
        stockPlusSearchClient = Feign.builder()
                .client(OkHttpClient())
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .logger(Slf4jLogger(StockPlusSearchClient::class.java))
                .logLevel(Logger.Level.FULL)
                .target(StockPlusSearchClient::class.java, "https://stockplus.com/api")
    }

    @Test
    @DisplayName("stockPlus 테스트 #1")
    fun testStockPlusFinancialStatementsClient() : Unit{
        val selectFinancialStatements = stockPlusSearchClient.selectFinancialStatements("KOREA-A000080")
        println(selectFinancialStatements)
    }

    @Test
    @DisplayName("stockPlus 테스트 #2")
    fun testStockPlusCompanyDataClient() : Unit{
        val selectCompanyData = stockPlusSearchClient.selectCompanyData("KOREA-A000080")
        println(selectCompanyData)
    }
}