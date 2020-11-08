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
    };

    fun getMonthlyDateGubun() : String {
        return "MM"
    }

    fun getDailyDateGubun() : String {
        return "DD"
    }
}