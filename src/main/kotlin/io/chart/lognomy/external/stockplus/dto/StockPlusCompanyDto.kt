package io.chart.lognomy.external.stockplus.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StockPlusCompanyDto (
        @JsonProperty("securityId") val securityId : String?,
        @JsonProperty("adjustedDividendYield") val adjustedDividendYield : String?,
        @JsonProperty("comment") val comment : String?,
        @JsonProperty("commonAdjustedOps") val commonAdjustedOps : Long?,
        @JsonProperty("consensusDate") val consensusDate : String?,
        @JsonProperty("consensusRating") val consensusRating : Long?,
        @JsonProperty("consensusRatingGrade") val consensusRatingGrade : Long?,
        @JsonProperty("consensusTargetPrice") val consensusTargetPrice : Long?,
//        @JsonProperty("id") val id : Long,
        @JsonProperty("isPer4Quarters") val isPer4Quarters: Boolean?,
        @JsonProperty("market") val market : String?,
        @JsonProperty("marketCapitalization") val marketCapitalization: Double?,
        @JsonProperty("operatingProfitMarginRate4Quarters") val operatingProfitMarginRate4Quarters : Double?,
        @JsonProperty("ownersAdjustedBps") val ownersAdjustedBps : Double?,
        @JsonProperty("ownersAdjustedEps") val ownersAdjustedEps : Double?,
        @JsonProperty("per4Quarters") val per4Quarters : Double?,
        @JsonProperty("salesGrowthRate3Years") val salesGrowthRate3Years : Double?,

        @JsonProperty("sectorAverage") val sectorAverage : StockPlusSectorAverageDto?,

        @JsonProperty("settlementDate") val settlementDate : String?,
        @JsonProperty("tickerSymbol") val tickerSymbol : String?,
        @JsonProperty("wicsSectorCode") val wicsSectorCode : String?,
        @JsonProperty("wicsSectorName") val wicsSectorName : String?
){
}