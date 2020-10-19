package io.chart.lognomy.config.circuitbreaker

import feign.RetryableException
import feign.Retryer

class CircuitNonRetryer : Retryer{
    override fun clone(): Retryer {
        return CircuitNonRetryer()
    }

    override fun continueOrPropagate(e: RetryableException?) {
        throw e!!
    }
}