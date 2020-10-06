package io.chart.lognomy.temporary

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@ActiveProfiles("testdocker")
@Transactional
@SpringBootTest
class EmployeeTest {

    @Autowired
    private lateinit var empService: EmpService

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var empRepository: EmpRepository

    @Autowired
    private lateinit var qEmpRepository: QEmpRepository

    @BeforeEach
    fun setup(){
        val jordan = Employee(1, "Jordan")
        empRepository.save(jordan)

        val police1 = Employee(2, "경찰관#1")
        empRepository.save(police1)

        val firefighter1 = Employee(3, "소방관#1")
        empRepository.save(firefighter1)
    }

    @Test
    @DisplayName("QueryDsl 설정 제대로 잡혔나 확인하는 테스트 코드 #1")
    fun testQuerydslConfigurationRepository(){
        val employees = qEmpRepository.selectAllEmployees()
        assertThat(employees.size).isEqualTo(3)
    }

    @Test
    @DisplayName("QueryDsl 설정 제대로 잡혔나 확인하는 테스트 코드 #2")
    fun testQuerydslConfigurationService(){
        val employees = empService.selectAllEmployees()
        assertThat(employees.size).isEqualTo(3)
    }

}