package io.chart.lognomy.external.stockplus.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StockPlusFinancialWrapperDto (
        @JsonProperty("quarterly") val quarterly : List<StockPlusFinancialDto>,
        @JsonProperty("yearly") val yearly : List<StockPlusFinancialDto>
) {
}