package io.chart.lognomy.external.stockplus.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StockPlusSectorAverageDto (
        @JsonProperty("createdAt") val createdAt : String,
        @JsonProperty("id") val id: Long,
        @JsonProperty("koreaBankBaseRate") val koreaBankBaseRate : Double,
        @JsonProperty("modifiedAt") val modifiedAt : String,
        @JsonProperty("operatingProfitMarginRate4Quarters") val operatingProfitMarginRate4Quarters : Double,
        @JsonProperty("per4Quarters") val per4Quarters : Double,
        @JsonProperty("salesGrowthRate3Years") val salesGrowthRate3Years : Double,
        @JsonProperty("wicsSectorCode") val wicsSectorCode : String,
        @JsonProperty("marketCapitalization") val marketCapitalization : Double
){
}