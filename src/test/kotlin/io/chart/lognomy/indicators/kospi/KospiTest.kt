package io.chart.lognomy.indicators.kospi

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KospiTest {

    lateinit var kospiService: KospiService

    @Qualifier("kospiRepository")
    @Autowired
    lateinit var kospiRepository: KospiRepository

    @BeforeEach
    fun setup() : Unit{
        kospiService = KospiService(kospiRepository)
    }

    @Test
    @DisplayName("findAll")
    fun testFindAll() : Unit{
        val findAll = kospiService.findAll()
        println(findAll)
    }
}