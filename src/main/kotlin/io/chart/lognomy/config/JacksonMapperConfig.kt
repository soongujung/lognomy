package io.chart.lognomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JacksonMapperConfig {

    @Bean
    @Primary
    open fun jacksonMapper() = ObjectMapper().apply{
        registerModule(KotlinModule())
    }
}