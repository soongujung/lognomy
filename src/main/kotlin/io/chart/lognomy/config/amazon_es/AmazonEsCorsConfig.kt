package io.chart.lognomy.config.amazon_es

import io.chart.lognomy.config.WebMvcConfig
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class AmazonEsCorsConfig {

    fun corsConfigurer(): WebMvcConfigurer{
        return WebMvcConfig()
    }
}