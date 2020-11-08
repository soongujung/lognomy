package io.chart.lognomy.external.korbank

import io.chart.lognomy.external.korbank.dto.daily.KorbankWrapperDailyDto
import io.chart.lognomy.external.korbank.dto.monthly.KorbankWrapperMonthlyDto

interface KorbankSearchService {
    fun selectMonthlyKospi (startDate : String, endDate: String) : KorbankWrapperMonthlyDto
    fun selectDailyKospi (startDate : String, endDate: String) : KorbankWrapperDailyDto
}