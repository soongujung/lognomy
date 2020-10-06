package io.chart.lognomy.temporary

import org.springframework.stereotype.Service

@Service
class EmpServiceImpl (val qEmpRepository: QEmpRepository) : EmpService{
    override fun selectAllEmployees(): List<Employee> {
        return qEmpRepository.selectAllEmployees()
    }
}