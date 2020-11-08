package io.chart.lognomy.external.korbank.dto.daily

import com.fasterxml.jackson.annotation.JsonProperty
import io.chart.lognomy.common.formatter.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class KorbankItemDailyDto (
        @JsonProperty("STAT_NAME") val statDesc : String,
        @JsonProperty("ITEM_CODE1") val midCategory : String,
        @JsonProperty("STAT_CODE") val topCategory : String,
        @JsonProperty("ITEM_NAME1") val dataName : String,
        @JsonProperty("DATA_VALUE") val dataValue : Double,
        @JsonProperty("TIME") val time : String
){

    var dateTime: LocalDateTime? = null
        get() {
            return processStrToLocalDate(time)
        }

    fun processStrToLocalDate(strDate : String): LocalDateTime {
        val formatter : String = DateTimeFormat.yyyyMMddHHmmss.formatter

        val ofPattern = DateTimeFormatter.ofPattern(formatter)
        val targetTime : String  = strDate + "235959"
        return LocalDateTime.parse(targetTime, ofPattern)
    }
}