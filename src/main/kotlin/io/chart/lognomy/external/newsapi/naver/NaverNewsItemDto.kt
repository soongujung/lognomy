package io.chart.lognomy.external.newsapi.naver

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverNewsItemDto (
        @JsonProperty("title")  val title: String,
        @JsonProperty("originallink")  val originallink: String,
        @JsonProperty("link")  val link: String,
        @JsonProperty("pubDate")  val pubDate: String,
        @JsonProperty("description") val description: String
){
}