package io.chart.lognomy.external.stockplus

import io.chart.lognomy.external.stockplus.dto.StockPlusCompanyDto
import io.chart.lognomy.external.stockplus.dto.StockPlusFinancialWrapperDto

interface StockPlusSearchService {
    fun selectFinancialStatements (stockCode : String) : StockPlusFinancialWrapperDto
    fun selectCompanyData (stockCode : String) : StockPlusCompanyDto
}