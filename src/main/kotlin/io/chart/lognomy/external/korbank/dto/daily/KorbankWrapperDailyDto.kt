package io.chart.lognomy.external.korbank.dto.daily

import com.fasterxml.jackson.annotation.JsonProperty

data class KorbankWrapperDailyDto (
        @JsonProperty("StatisticSearch") val statisticSearch : KorbankListDailyDto
){
}