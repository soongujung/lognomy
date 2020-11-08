package io.chart.lognomy.external.korbank

import io.chart.lognomy.external.korbank.dto.daily.KorbankWrapperDailyDto
import io.chart.lognomy.external.korbank.dto.monthly.KorbankWrapperMonthlyDto
import io.chart.lognomy.external.korbank.type.KorbankSearchType
import org.springframework.stereotype.Service

@Service
class KorbankSearchServiceImpl (
        val korbankSearchClient : KorbankSearchClient
) : KorbankSearchService {

    override fun selectMonthlyKospi(startDate: String, endDate: String): KorbankWrapperMonthlyDto {
        val kospiList = korbankSearchClient.selectMonthlyKospi(
                topCategory = KorbankSearchType.KOSPI_MONTHLY.topCategory,
                midCategory = KorbankSearchType.KOSPI_MONTHLY.midCategory,
                dateGubun = KorbankSearchType.KOSPI_MONTHLY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return kospiList
    }

    override fun selectDailyKospi(startDate: String, endDate: String): KorbankWrapperDailyDto {
        val kospiList = korbankSearchClient.selectDailyKospi(
                topCategory = KorbankSearchType.KOSPI_DAILY.topCategory,
                midCategory = KorbankSearchType.KOSPI_DAILY.midCategory,
                dateGubun = KorbankSearchType.KOSPI_DAILY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return kospiList
    }
}