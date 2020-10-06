package io.chart.lognomy.temporary

import org.springframework.data.jpa.repository.JpaRepository

interface EmpRepository : JpaRepository<Employee, Long>{
}