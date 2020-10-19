package io.chart.lognomy.config.circuitbreaker.newsapi

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NaverNewsSearchHeaderConfig (
        @Value("\${conn.naver.news.client-id}") private val naverClientId: String,
        @Value("\${conn.naver.news.x-naver-client-secret}") private val naverClientSecret: String
){

    private val naverClientIdHeader: String = "X-Naver-Client-Id:$naverClientId"
    private val naverClientSecretHeader: String = "X-Naver-Client-Secret:$naverClientSecret"

    @Bean
    fun requestInterceptor() :RequestInterceptor{
        return RequestInterceptor {
            template: RequestTemplate? ->
                template?.header(
                        naverClientIdHeader, naverClientSecretHeader
                )
        }
    }
}