package io.chart.lognomy.external.stockplus

import feign.Param
import feign.RequestLine
import io.chart.lognomy.external.stockplus.dto.StockPlusCompanyDto
import io.chart.lognomy.external.stockplus.dto.StockPlusFinancialWrapperDto
import org.springframework.web.bind.annotation.ResponseBody

interface StockPlusSearchClient {
    @ResponseBody
    @RequestLine("GET /companies/{stockCode}/financial_statements.json")
    fun selectFinancialStatements (
            @Param("stockCode") stockCode: String
    ) : StockPlusFinancialWrapperDto

    @ResponseBody
    @RequestLine("GET /companies/{stockCode}.json")
    fun selectCompanyData (
            @Param("stockCode") stockCode: String
    ) : StockPlusCompanyDto
}