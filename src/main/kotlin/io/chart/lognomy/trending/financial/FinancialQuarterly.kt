package io.chart.lognomy.trending.financial

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

// 참고자료
// https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.rest

// TODO::
//  Feign 으로 부터 받아오는 문자열 숫자 데이터를 숫자 자료형(Double)으로 변환하는 공통 기능 필요
@Document(indexName = "financial_quarterly", createIndex = false)
data class FinancialQuarterly (

        @Id @Field(name = "stock_code") val stockCode : String,
        @Field(name = "base_date", type = FieldType.Date, pattern = "yyyy-MM-dd'T'hh:mm:ss", format = DateFormat.date_hour_minute_second)
        val baseDate : LocalDateTime,

        @Field(name = "sales", type = FieldType.Double) val sales : Double,
        @Field(name = "net_profit_margin_rate") val netProfitMarginRate : Double,
        @Field(name = "owners_net_profit") val ownersNetProfit : Double,
        @Field(name = "operating_profit") val operatingProfit : Double,
        @Field(name = "operating_profit_margin_rate") val operatingProfitMarginRate : Double,
        @Field(name = "is_connect") val isConnect : Boolean,
        @Field(name = "pbr") val pbr : Double,
        @Field(name = "per") val per : Double,
        @Field(name = "roe") val roe : Double
)