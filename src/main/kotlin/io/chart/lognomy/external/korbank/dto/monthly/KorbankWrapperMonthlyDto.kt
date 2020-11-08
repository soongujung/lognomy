package io.chart.lognomy.external.korbank.dto.monthly

import com.fasterxml.jackson.annotation.JsonProperty

data class KorbankWrapperMonthlyDto (
        @JsonProperty("StatisticSearch") val statisticSearch : KorbankListMonthlyDto
){
}