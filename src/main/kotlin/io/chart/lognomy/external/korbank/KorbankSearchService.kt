package io.chart.lognomy.external.korbank

import io.chart.lognomy.external.korbank.dto.daily.KorbankWrapperDailyDto
import io.chart.lognomy.external.korbank.dto.monthly.KorbankWrapperMonthlyDto

interface KorbankSearchService {
    fun selectMonthlyKospi (startDate : String, endDate: String) : KorbankWrapperMonthlyDto
    fun selectDailyKospi (startDate : String, endDate: String) : KorbankWrapperDailyDto
    fun selectMonthlyInterestRateKR (startDate : String, endDate: String) : KorbankWrapperMonthlyDto
    fun selectDailyInterestRateKR (startDate : String, endDate: String) : KorbankWrapperDailyDto
    fun selectMonthlyInterestRateUS (startDate : String, endDate: String) : KorbankWrapperMonthlyDto
    fun selectDailyInterestRateUS (startDate : String, endDate: String) : KorbankWrapperDailyDto

    fun selectMonthlyExchangeRateDollar(startDate : String, endDate: String) : KorbankWrapperMonthlyDto
    fun selectDailyExchangeRateDollar(startDate : String, endDate: String) : KorbankWrapperDailyDto
}