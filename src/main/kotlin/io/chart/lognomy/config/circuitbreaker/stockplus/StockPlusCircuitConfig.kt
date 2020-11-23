package io.chart.lognomy.config.circuitbreaker.stockplus

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import io.chart.lognomy.external.stockplus.StockPlusSearchClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StockPlusCircuitConfig (
        @Value("\${conn.stockplus.baseUrl}")
        private val baseUrl : String
){

    @Bean
    fun stockPlusFeignClient() : StockPlusSearchClient {
        val searchClient = Feign.builder()
                .client(OkHttpClient())
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .target(StockPlusSearchClient::class.java, baseUrl)

        return searchClient
    }
}