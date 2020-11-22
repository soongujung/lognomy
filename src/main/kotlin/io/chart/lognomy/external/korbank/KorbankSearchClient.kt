package io.chart.lognomy.external.korbank

import feign.Param
import feign.RequestLine
import io.chart.lognomy.external.korbank.dto.daily.KorbankWrapperDailyDto
import io.chart.lognomy.external.korbank.dto.monthly.KorbankWrapperMonthlyDto
import org.springframework.web.bind.annotation.ResponseBody

interface KorbankSearchClient {

    /**
     * KOSPI (종가)
     * 일별
     *  대분류 064Y001 / 중분류 0001000
     *  http://ecos.bok.or.kr/api/StatisticSearch/--/json/kr/1/10/064Y001/DD/20190101/20190630/0001000/
     *
     * 월별 (시가총액, 거래량, 거래 대금 등등 제공되는 데이터의 종류가 매우 많다.)
     *  대분류 028Y015 / 중분류 1070000
     *  http://ecos.bok.or.kr/api/StatisticSearch/--/json/kr/1/10/028Y015/MM/201901/201906/1070000/
     *
     *  결정은 일단은 모든 데이터를 월별로 제공하고 세부 데이터는 따로 기획을 해서 미시경제를 볼수 있도록 할 예정이다.
     */

    // 일별 검색
    @ResponseBody
//    @RequestLine("GET /json/kr/1/20000/028Y015/MM/201901/201906/1070000")
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectMonthlyKospi (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperMonthlyDto

    @ResponseBody
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectDailyKospi (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperDailyDto

    /**
     * 기준 금리 (국가별 통계검색)
     *  : 월별 검색 코드만을 제공하고 있다.
     * 월별
     *  대분류 I10Y014 / 중분류 (KR|US)
     *  http://ecos.bok.or.kr/api/StatisticSearch/--/json/kr/1/20000/I10Y014/MM/196001/202012/KR/
     */
    @ResponseBody
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectMonthlyInterestRate (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperMonthlyDto

    @ResponseBody
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectDailyInterestRate (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperMonthlyDto

    /**
     * 원/달러 환율
     * 일별
     *  대분류 036Y001
     *  http://ecos.bok.or.kr/api/StatisticSearch/--/json/kr/1/20000/036Y001/DD/20140101/20190131/0000001
     * 월별
     *  대분류 036Y001
     *  http://ecos.bok.or.kr/api/StatisticSearch/--/json/kr/1/20000/036Y001/MM/201401/201901/0000001
     */

    /**
     * 월별
     */
    @ResponseBody
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectMonthlyExchangeRateDollar (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperMonthlyDto

    /**
     * 일별
     */
    @ResponseBody
    @RequestLine("GET /json/kr/1/20000/{topCategory}/{dateGubun}/{startDate}/{endDate}/{midCategory}")
    fun selectDailyExchangeRateDollor (
            @Param("topCategory") topCategory: String,
            @Param("midCategory") midCategory: String,
            @Param("dateGubun") dateGubun: String,
            @Param("startDate") startDate: String,
            @Param("endDate") endDate: String,
    ) : KorbankWrapperDailyDto

}