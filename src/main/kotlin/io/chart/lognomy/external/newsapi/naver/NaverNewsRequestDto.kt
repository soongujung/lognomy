package io.chart.lognomy.external.newsapi.naver

import feign.Param

data class NaverNewsRequestDto (
        private val query: String,
        private val display: Int,
        private val start: Int,
        private val sort: String
){
}