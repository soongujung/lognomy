package io.chart.lognomy.newsapi.naver

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class NaverNewsListController (
        private val naverNewsSearchClient: NaverNewsSearchClient
){

    @ResponseBody
    @GetMapping("/newsapi/naver/{keyword}")
    fun getNaverNewsList(@PathVariable("keyword") keyword: String): NaverNewsListDto{
        return naverNewsSearchClient
                .fetchNewsList(query = keyword, display = 30, start = 1, sort = "date")
    }

}

