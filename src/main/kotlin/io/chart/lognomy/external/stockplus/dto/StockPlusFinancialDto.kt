package io.chart.lognomy.external.stockplus.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StockPlusFinancialDto (
        @JsonProperty("baseDate") val baseDate : String,
        @JsonProperty("createdAt") val createdAt : String,
        @JsonProperty("id") val id: Long,
        @JsonProperty("modifiedAt") val modifiedAt: String?,
        @JsonProperty("netProfitMarginRate") val netProfitMarginRate: Double?,
        @JsonProperty("operatingProfit") val operatingProfit: Double?,
        @JsonProperty("operatingProfitMarginRate") val operatingProfitMarginRate: Double,
        @JsonProperty("ownersNetProfit") val ownersNetProfit: Double,
        @JsonProperty("pbr") val pbr: Double?,
        @JsonProperty("per") val per: Double?,
        @JsonProperty("roe") val roe: Double?,
        @JsonProperty("sales") val sales: Double?,
        @JsonProperty("tickerSymbol") val tickerSymbol: String?,
        @JsonProperty("type") val type: String,
        @JsonProperty("isConsensus") val isConsensus: Boolean?,
        @JsonProperty("isConnect") val isConnect: Boolean?
){
}