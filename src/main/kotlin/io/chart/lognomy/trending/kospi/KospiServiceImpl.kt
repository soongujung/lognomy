package io.chart.lognomy.trending.kospi

import org.springframework.stereotype.Service

@Service
class KospiServiceImpl (
        val kospiRepository: KospiRepository
) : KospiService{
    public override fun findAll(): List<Kospi> {
        return kospiRepository.findAllBy().toList()
    }
}