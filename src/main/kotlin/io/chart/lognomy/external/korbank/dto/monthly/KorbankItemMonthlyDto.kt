package io.chart.lognomy.external.korbank.dto.monthly

import com.fasterxml.jackson.annotation.JsonProperty

data class KorbankItemMonthlyDto (
        @JsonProperty("UNIT_NAME") val unitName : String,
        @JsonProperty("STAT_NAME") val statDesc : String,
        @JsonProperty("STAT_CODE") val statCode : String,

        @JsonProperty("ITEM_CODE1") val itemCode1 : String,
        @JsonProperty("ITEM_CODE2") val itemCode2 : String,
        @JsonProperty("ITEM_CODE3") val itemCode3 : String,

        @JsonProperty("ITEM_NAME1") val itemName1 : String,
        @JsonProperty("ITEM_NAME2") val itemName2 : String,
        @JsonProperty("ITEM_NAME3") val itemName3 : String,

        @JsonProperty("DATA_VALUE") val dataValue : Double,
        @JsonProperty("TIME") val time : String
){
    /**
     * time 필드의 데이터 타입을 변환(String -> LocalDate) 하는 데에 문제가 있다.
     * 한국은행 API 에서 넘어오는 202001 과 같은 형식의 데이터는 뒤에 DD 가 붙어있지 않기에 불완전하다. Temporal Acceossor 관련 에러를 낸다.
     *
     * 에러 로그 : java.time.DateTimeException: Unable to obtain LocalDate from TemporalAccessor: {Year=2020, MonthOfYear=1},ISO of type java.time.format.Parsed
     * 참고자료 : https://stackoverflow.com/questions/45320971/java-unable-to-obtain-localdate-from-temporalaccessor
     *
     * 이런 이유로... REST API 에서 가져온 데이터는 그대로 두고, Bulk API 로 Insert 시에 별도의 매핑작업을 거치도록 BULK 를 위한 Dto인 BulkDto 를 따로 작성하자.
     * 데이터 타입을 korbank에 의존적으로 하기보다는 batch 등과 연동시 유연하게 우리가 필요한 데이터만을 포함하도록 필요한 정보만 세팅해서 Elastic Search 로 보내보자.
     */
}