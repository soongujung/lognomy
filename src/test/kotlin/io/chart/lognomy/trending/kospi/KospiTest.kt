package io.chart.lognomy.trending.kospi

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KospiTest {

    lateinit var kospiServiceImpl: KospiServiceImpl

    @Qualifier("kospiRepository")
    @Autowired
    lateinit var kospiRepository: KospiRepository

    @BeforeEach
    fun setup() : Unit{
        kospiServiceImpl = KospiServiceImpl(kospiRepository)
    }

    @Test
    @DisplayName("findAll")
    fun testFindAll() : Unit{
        val findAll = kospiServiceImpl.findAll()
        println(findAll)
    }
}