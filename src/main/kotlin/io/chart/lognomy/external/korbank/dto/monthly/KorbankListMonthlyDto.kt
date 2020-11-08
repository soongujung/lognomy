package io.chart.lognomy.external.korbank.dto.monthly

import com.fasterxml.jackson.annotation.JsonProperty

data class KorbankListMonthlyDto (
        @JsonProperty("list_total_count") val list_total_count : Int,
        @JsonProperty("row") val row : List<KorbankItemMonthlyDto>
){
}