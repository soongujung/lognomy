package io.chart.lognomy.config.amazon_es

import com.amazonaws.DefaultRequest
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.Signer
import com.amazonaws.http.HttpMethodName
import org.apache.http.*
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.BasicHttpEntity
import org.apache.http.message.BasicHeader
import org.apache.http.protocol.HttpContext
import org.apache.http.protocol.HttpCoreContext
import java.io.ByteArrayInputStream
import java.net.URI
import java.util.*

/**
 * 참고자료
 *      https://github.com/awslabs/aws-request-signing-apache-interceptor
 * 위 링크의 출처)
 *      https://docs.aws.amazon.com/ko_kr/elasticsearch-service/latest/developerguide/es-request-signing.html
 */
class AmazonEsInterceptor (
    private val service : String,
    private val signer : Signer,
    private val awsCredentialsProvider: AWSCredentialsProvider
): HttpRequestInterceptor{

    override fun process(request: HttpRequest?, context: HttpContext?) {
        var uriBuilder: URIBuilder = URIBuilder(request?.requestLine?.uri)

        var signableRequest = DefaultRequest<Any>(service)
        var host : HttpHost = context?.getAttribute(HttpCoreContext.HTTP_TARGET_HOST) as HttpHost
        signableRequest.endpoint = URI.create(host.toURI())

        val httpMethod : HttpMethodName = HttpMethodName.fromValue(request?.requestLine?.method)
        signableRequest.httpMethod = httpMethod
        signableRequest.resourcePath = uriBuilder.build().rawPath

        if(request is HttpEntityEnclosingRequest){
            val httpEntityEnclosingRequest :HttpEntityEnclosingRequest = request as HttpEntityEnclosingRequest
            if (httpEntityEnclosingRequest.entity == null){
//                b : ByteArray = ByteArray(1,2,3)
                signableRequest.content = ByteArrayInputStream(ByteArray(1))
            }
            else{
                signableRequest.content = httpEntityEnclosingRequest.entity.content
            }
        }

        signableRequest.parameters = nvpToMapParams(uriBuilder.queryParams)
        if (request != null) {
            signableRequest.headers = headerArrayToMap(request.allHeaders)
        }

        signer.sign(signableRequest, awsCredentialsProvider.credentials)

        request?.setHeaders(mapToHeaderArray(signableRequest.headers))

        if (request is HttpEntityEnclosingRequest){
            var httpEntityEnclosingRequest = request as HttpEntityEnclosingRequest
            if ( httpEntityEnclosingRequest.entity != null){
                var basicHttpEntity = BasicHttpEntity()
                basicHttpEntity.content = signableRequest.content
                httpEntityEnclosingRequest.entity = basicHttpEntity
            }
        }
    }

    private fun nvpToMapParams(params : List<NameValuePair>) : Map<String, List<String>> {
        var parameterMap : Map<String, List<String>> = TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
        params.forEach {
//            var argsList : List<String> = it.name
            var argList = mutableListOf<String>()
            argList.add(it.value)
        }
        return parameterMap
    }

    private fun headerArrayToMap(headers : Array<Header>) : Map<String, String>{
        val headersMap = TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER)
        headers.forEach {
            if (!skipHeader(it)){
                headersMap.put(it.name, it.value)
            }
        }
        return headersMap
    }

    private fun skipHeader(header: Header) : Boolean {
        val isContsLengthKeyNameRight = "content-length".equals(header.name, ignoreCase = true)
        val isHeaderValueZero = "0".equals(header.value)
        val isHeaderKeyNameRight = "host".equals(header.name, ignoreCase = true)

        return (isContsLengthKeyNameRight && isHeaderValueZero) || isHeaderKeyNameRight
    }

    fun mapToHeaderArray (mapHeaders : Map<String, String>) : Array<Header> {
        var headers = arrayListOf<Header>()

        mapHeaders.forEach {
//            headers[i++] = BasicHeader(it.key, it.value)
            headers.add(BasicHeader(it.key, it.value))
        }
        return headers.toTypedArray()
    }
}