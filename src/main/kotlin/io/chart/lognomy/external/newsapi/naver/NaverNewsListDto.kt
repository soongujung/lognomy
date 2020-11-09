package io.chart.lognomy.external.newsapi.naver

import com.fasterxml.jackson.annotation.JsonProperty
import io.chart.lognomy.external.newsapi.naver.NaverNewsItemDto

class NaverNewsListDto (
        @JsonProperty("lastBuildDate") val lastBuildDate: String,
        @JsonProperty("total") val total: Long,
        @JsonProperty("start") val start: Long,
        @JsonProperty("display") val display: Long,
        @JsonProperty("items") @SuppressWarnings("unchecked") val items: List<NaverNewsItemDto>
){
}