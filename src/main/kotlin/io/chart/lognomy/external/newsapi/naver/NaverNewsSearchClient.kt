package io.chart.lognomy.external.newsapi.naver


import feign.Headers
import feign.Param
import feign.RequestLine
import io.chart.lognomy.external.newsapi.naver.NaverNewsListDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Headers("X-Naver-Client-Id:--", "X-Naver-Client-Secret:--")
//@Headers("\${conn.naver.news.header.client-id}", "\${conn.naver.news.header.x-naver-client-secret}")
//TODO @FeignClient 의 configuration 에 Config를 지정하는 것이 안된다. 이부분 찾아봐야 한다.
//@FeignClient(configuration = [NaverNewsSearchHeaderConfig::class], name = "naver-news-search-client")
//@FeignClient(name = "naver-news-search-client")
interface NaverNewsSearchClient {

    @ResponseBody
    @GetMapping("/v1/search/news.json")
    @RequestLine("GET /v1/search/news.json?query={query}&display={display}&start={start}&sort={sort}")
    fun fetchNewsList(
            @Param("query") query: String,
            @Param("display") display: Int,
            @Param("start") start: Int,
            @Param("sort") sort: String
    ): NaverNewsListDto
}