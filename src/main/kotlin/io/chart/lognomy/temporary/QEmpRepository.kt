package io.chart.lognomy.temporary

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class QEmpRepository(
        val entityManager: EntityManager,
        val jpaQueryFactory: JPAQueryFactory
) {
    fun selectAllEmployees(): List<Employee> {

        val empList : List<Employee> = jpaQueryFactory.selectFrom(QEmployee.employee)
                .fetch();

        return empList
    }
}