package io.chart.lognomy.external.korbank.type

enum class KorbankSearchType (
        val searchType: String,
        val topCategory: String,
        val midCategory: String,
        val dateGubun: String
){
    KOSPI_MONTHLY(
            searchType = "KOSPI_MONTHLY",
            topCategory = "028Y015",
            midCategory = "1070000",
            dateGubun = "MM"
    ){
    },
    KOSPI_DAILY(
            searchType =  "KOSPI_DAILY",
            topCategory = "064Y001",
            midCategory = "0001000",
            dateGubun = "DD"
    ){
    },
    INTEREST_RATE_KR_MONTHLY(
            searchType = "INTEREST_RATE_MONTHLY",
            topCategory = "I10Y014",
            midCategory = "KR",
            dateGubun = "MM"
    ){
    },
    INTEREST_RATE_KR_DAILY( // 일별 조회 조건이 들어오면 MM으로 묵시적 변환 후 리턴
            searchType = "INTEREST_RATE_DAILY",
            topCategory = "I10Y014",
            midCategory = "KR",
            dateGubun = "MM"
    ){
    },
    INTEREST_RATE_US_MONTHLY(
            searchType = "INTEREST_RATE_MONTHLY",
            topCategory = "I10Y014",
            midCategory = "US",
            dateGubun = "MM"
    ){
    },
    INTEREST_RATE_US_DAILY(
            topCategory = "I10Y014",
            searchType = "INTEREST_RATE_DAILY",
            midCategory = "US",
            dateGubun = "MM"
    ){
    },
    EXCHANGE_RATE_DOLLAR_MONTHLY(
            topCategory = "036Y001",
            searchType = "EXCHANGE_RATE_DOLLAR_MONTHLY",
            midCategory = "0000001",
            dateGubun = "MM"
    ){
    },
    EXCHANGE_RATE_DOLLAR_DAILY(
            topCategory = "036Y001",
            searchType = "EXCHANGE_RATE_DOLLAR_DAILY",
            midCategory = "0000001",
            dateGubun = "DD"
    ){
    };

    fun getMonthlyDateGubun() : String {
        return "MM"
    }

    fun getDailyDateGubun() : String {
        return "DD"
    }
}