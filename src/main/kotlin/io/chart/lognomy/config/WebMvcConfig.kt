package io.chart.lognomy.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:9090", // vue.js running port
                        "http://localhost:3000"  // react running port
                )
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        // TODO : header에 naver secret key, client id 설정하는 코드 추가 필요
    }
}