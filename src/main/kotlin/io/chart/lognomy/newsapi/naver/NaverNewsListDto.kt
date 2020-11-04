package io.chart.lognomy.newsapi.naver

import com.fasterxml.jackson.annotation.JsonProperty

class NaverNewsListDto (
        @JsonProperty("lastBuildDate") val lastBuildDate: String,
        @JsonProperty("total") val total: Long,
        @JsonProperty("start") val start: Long,
        @JsonProperty("display") val display: Long,
        @JsonProperty("items") @SuppressWarnings("unchecked") val items: List<NaverNewsItemDto>
){
}