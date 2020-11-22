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

    override fun selectMonthlyInterestRateKR(startDate: String, endDate: String): KorbankWrapperMonthlyDto {
        val result = korbankSearchClient.selectMonthlyInterestRate(
                topCategory = KorbankSearchType.INTEREST_RATE_KR_MONTHLY.topCategory,
                midCategory = KorbankSearchType.INTEREST_RATE_KR_MONTHLY.midCategory,
                dateGubun = KorbankSearchType.INTEREST_RATE_KR_MONTHLY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return result
    }

    override fun selectDailyInterestRateKR(startDate: String, endDate: String): KorbankWrapperDailyDto {
        TODO("Not yet implemented")
    }

    override fun selectMonthlyInterestRateUS(startDate: String, endDate: String): KorbankWrapperMonthlyDto {
        val result = korbankSearchClient.selectMonthlyInterestRate(
                topCategory = KorbankSearchType.INTEREST_RATE_US_MONTHLY.topCategory,
                midCategory = KorbankSearchType.INTEREST_RATE_US_MONTHLY.midCategory,
                dateGubun = KorbankSearchType.INTEREST_RATE_US_MONTHLY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return result
    }

    override fun selectDailyInterestRateUS(startDate: String, endDate: String): KorbankWrapperDailyDto {
        TODO("Not yet implemented")
    }

    override fun selectMonthlyExchangeRateDollar(startDate: String, endDate: String): KorbankWrapperMonthlyDto {
        val result = korbankSearchClient.selectMonthlyExchangeRateDollar(
                topCategory = KorbankSearchType.EXCHANGE_RATE_DOLLAR_MONTHLY.topCategory,
                midCategory = KorbankSearchType.EXCHANGE_RATE_DOLLAR_MONTHLY.midCategory,
                dateGubun = KorbankSearchType.EXCHANGE_RATE_DOLLAR_MONTHLY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return result
    }

    override fun selectDailyExchangeRateDollar(startDate: String, endDate: String): KorbankWrapperDailyDto {
        val result = korbankSearchClient.selectDailyExchangeRateDollor(
                topCategory = KorbankSearchType.EXCHANGE_RATE_DOLLAR_DAILY.topCategory,
                midCategory = KorbankSearchType.EXCHANGE_RATE_DOLLAR_DAILY.midCategory,
                dateGubun = KorbankSearchType.EXCHANGE_RATE_DOLLAR_DAILY.dateGubun,
                startDate = startDate,
                endDate = endDate
        )
        return result
    }
}