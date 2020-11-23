package io.chart.lognomy.external.stockplus

import io.chart.lognomy.external.stockplus.dto.StockPlusCompanyDto
import io.chart.lognomy.external.stockplus.dto.StockPlusFinancialWrapperDto
import org.springframework.stereotype.Service

@Service
class StockPlusSearchServiceImpl (
        val stockPlusSearchClient : StockPlusSearchClient
) : StockPlusSearchService {

    override fun selectFinancialStatements(stockCode: String): StockPlusFinancialWrapperDto {
        val result = stockPlusSearchClient.selectFinancialStatements(stockCode)
        return result
    }

    override fun selectCompanyData(stockCode: String): StockPlusCompanyDto {
        val result = stockPlusSearchClient.selectCompanyData(stockCode)
        return result
    }
}