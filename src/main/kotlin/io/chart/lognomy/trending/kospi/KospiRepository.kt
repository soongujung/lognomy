package io.chart.lognomy.trending.kospi

//import org.springframework.stereotype.Repository

import org.springframework.data.repository.Repository
import java.time.LocalDateTime


interface KospiRepository : Repository<Kospi, LocalDateTime> {
    fun findAllBy(): List<Kospi>
}