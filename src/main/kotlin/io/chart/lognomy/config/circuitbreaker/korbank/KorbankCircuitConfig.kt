package io.chart.lognomy.config.circuitbreaker.korbank

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import io.chart.lognomy.external.korbank.KorbankSearchClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
data class KorbankCircuitConfig (
        @Value("\${conn.korbank.api.key}")
        private val apiKey : String,
        @Value("\${conn.korbank.api.baseUrl}")
        private val baseUrl : String,
        @Value("\${conn.korbank.api.baseUrl.long}")
        private val longBaseUrl : String,
){

        @Bean
        fun korbankKospiFeignClient() : KorbankSearchClient {
                val korbankSearchClient = Feign.builder()
                        .client(OkHttpClient())
                        .encoder(JacksonEncoder())
                        .decoder(JacksonDecoder())
                        .logLevel(Logger.Level.FULL)
                        .target(KorbankSearchClient::class.java, longBaseUrl)
                return korbankSearchClient
        }
}