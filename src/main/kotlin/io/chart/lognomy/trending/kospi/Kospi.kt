package io.chart.lognomy.trending.kospi

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime


// 참고자료
// https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.rest

@Document(indexName = "kospi", createIndex = false)
data class Kospi(
        @Id @Field(name = "TIME", type = FieldType.Date, pattern = "yyyy-MM-dd'T'hh:mm:ss", format = DateFormat.date_hour_minute_second)
        val time : LocalDateTime,

        @Field(name = "DATA_VALUE", type = FieldType.Text) val dataValue : Double,
        @Field(name = "ITEM_NAME1", type = FieldType.Text) val dataName : String,

        @Field(name = "UNIT_NAME", type = FieldType.Text) val unitName : String,
        @Field(name = "STAT_CODE", type = FieldType.Text) val topCategory : String,
        @Field(name = "ITEM_CODE1", type = FieldType.Text) val midCategory : String,
        @Field(name = "STAT_NAME", type = FieldType.Text) val statDesc : String,

        // -- 아래의 필드 들은 나중에 지울지 말지 결정이 필요하다.
        @Field(name = "ITEM_CODE2", type = FieldType.Text) val itemCodeTwo : String,
        @Field(name = "ITEM_CODE3", type = FieldType.Text) val itemCodeThree : String,
        @Field(name = "ITEM_NAME2", type = FieldType.Text) val itemNameTwo : String,
        @Field(name = "ITEM_NAME3", type = FieldType.Text) val itemNameThree : String,
)

