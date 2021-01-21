package io.chart.lognomy.config.amazon_es

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSCredentialsConfig (
    @Value("\${conn.elasticsearch.accessKey}")
    private val accessKey: String,

    @Value("\${conn.elasticsearch.secretKey}")
    private val secretKey: String
){

    @Bean(name = ["devCredentialsProvider"])
    fun devCredentialsProvider(): AWSStaticCredentialsProvider {
        val credential = BasicAWSCredentials(accessKey, secretKey)
        return AWSStaticCredentialsProvider(credential)
    }
}