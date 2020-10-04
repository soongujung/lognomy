package io.chart.lognomy.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:/connection.properties")
class PropertyConfig {
}