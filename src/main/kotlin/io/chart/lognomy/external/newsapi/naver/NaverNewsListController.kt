package io.chart.lognomy.external.newsapi.naver

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
    fun getNaverNewsList(@PathVariable("keyword") keyword: String): NaverNewsListDto {
        return naverNewsSearchClient
                .fetchNewsList(query = keyword, display = 30, start = 1, sort = "date")
    }

}

