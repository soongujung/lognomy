package io.chart.lognomy.external.stockplus.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StockPlusFinancialDto (
        @JsonProperty("baseDate") val baseDate : String,
        @JsonProperty("createdAt") val createdAt : String,
        @JsonProperty("id") val id: Long,
        @JsonProperty("modifiedAt") val modifiedAt: String?,
        // 매출액
        @JsonProperty("sales") val sales: Double?,
        // 순이익률
        @JsonProperty("netProfitMarginRate") val netProfitMarginRate: Double?,
        // 당기순이익
        @JsonProperty("ownersNetProfit") val ownersNetProfit: Double,
        // 영업이익
        @JsonProperty("operatingProfit") val operatingProfit: Double?,
        // 영업이익율
        @JsonProperty("operatingProfitMarginRate") val operatingProfitMarginRate: Double,

        // 연결재무제표 여부
        @JsonProperty("isConnect") val isConnect: Boolean?,
        @JsonProperty("pbr") val pbr: Double?,
        @JsonProperty("per") val per: Double?,
        @JsonProperty("roe") val roe: Double?,
        @JsonProperty("tickerSymbol") val tickerSymbol: String?,
        @JsonProperty("type") val type: String,
        @JsonProperty("isConsensus") val isConsensus: Boolean?
){
}