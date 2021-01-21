package io.chart.lognomy.config.amazon_es

import com.amazonaws.auth.AWS4Signer
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import org.apache.http.HttpHost

import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import java.util.*

@Configuration
class AmazonEsClientConfig (
        @Value("\${conn.elasticsearch.host}") val host: String,
        @Value("\${conn.elasticsearch.port}") val port: String,
//        @Value("\${conn.elasticsearch.cluster_name}") val clusterName: String,
        @Value("\${conn.elasticsearch.username}") val username: String,
        @Value("\${conn.elasticsearch.password}") val password: String,
        @Value("\${conn.elasticsearch.region}") val region: String
) : AbstractElasticsearchConfiguration() {

//    private val credentialsProvider : AWSCredentialsProvider = DefaultAWSCredentialsProviderChain()
    @Autowired
    @Qualifier("devCredentialsProvider")
    lateinit var credentialsProvider: AWSStaticCredentialsProvider

    @Bean(name = ["amazonEsClient"])
    override fun elasticsearchClient(): RestHighLevelClient {
        val encodedBytes : String = Base64.getEncoder().encodeToString("$username:$password".toByteArray())

//        var host = "$host:$port"
        var host = "$host"
        val serviceName = "es"
        val signer = AWS4Signer()

        signer.serviceName = serviceName
        signer.regionName = region
        var interceptor = AmazonEsInterceptor(service = serviceName, signer = signer, awsCredentialsProvider = credentialsProvider)

        // 참고자료
        // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-getting-started-initialization.html
        val rest = RestClient
                .builder(HttpHost(host))
                .setHttpClientConfigCallback { it ->
                    it.addInterceptorLast(interceptor)
                }
        return RestHighLevelClient(rest)
    }

}