package io.chart.lognomy.external.korbank.dto.daily

import com.fasterxml.jackson.annotation.JsonProperty

data class KorbankListDailyDto (
        @JsonProperty("list_total_count") val list_total_count : Int,
        @JsonProperty("row") val row : List<KorbankItemDailyDto>
){
}