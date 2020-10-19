package io.chart.lognomy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class LognomyApplication

fun main(args: Array<String>) {
    runApplication<LognomyApplication>(*args)
}
