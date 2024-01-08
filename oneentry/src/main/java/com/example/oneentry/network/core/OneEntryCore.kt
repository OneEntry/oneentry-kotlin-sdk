package com.example.oneentry.network.core

import com.example.oneentry.model.OneEntryException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal val Map<String, Any?>.query: String
    get() {
        val parameters = this
            .filterValues { it != null }
            .map { (key, value) -> "$key=$value" }
            .joinToString("&")

        return if (parameters.isEmpty()) "" else "?$parameters"
    }

class OneEntryCore private constructor() {

    private var client: HttpClient? = null
//    private var domain: String? = null
    internal val api = "/api/content"
    val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private val defaultRequestBuilder: HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Get
    }

    companion object {

        val instance: OneEntryCore = OneEntryCore()

        fun initializeApp(domain: String, credential: OneEntryCredential) {

//            instance.domain = domain
            instance.client = credential.client.config {

                expectSuccess = true

                defaultRequest {
                    url {
                        host = domain
                        protocol = URLProtocol.HTTPS
                        path(instance.api)
                    }
                }

                install(ContentNegotiation) {
                    json(instance.serializer)
                }

                HttpResponseValidator {
                    handleResponseExceptionWithRequest { exception, _ ->
                        val responseException = exception as? ResponseException ?: return@handleResponseExceptionWithRequest
                        val response = responseException.response
                        val oneEntryException = instance.serializer.decodeFromString<OneEntryException>(response.bodyAsText())
                        throw oneEntryException
                    }
                }
            }
        }
    }

    internal suspend inline fun <reified T>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        noinline block: HttpRequestBuilder.() -> Unit = defaultRequestBuilder
    ): T {

        val client = client ?: throw RuntimeException("OneEntry application has not been initialized")
        val url = link + parameters.query
        println("Url: $url")
        val response = client.request(url, block)

        println("Response: " + response.bodyAsText())

        return response.body()
    }

    internal suspend inline fun <reified T, reified G>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        body: G,
        noinline block: HttpRequestBuilder.() -> Unit = defaultRequestBuilder
    ): T {

        val client = client ?: throw RuntimeException("OneEntry application has not been initialized")
        val response = client.request(link + parameters.query, block)

        return serializer.decodeFromString(response.bodyAsText())
    }

    internal suspend inline fun requestData(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        noinline block: HttpRequestBuilder.() -> Unit = defaultRequestBuilder
    ) {

        val client = client ?: throw RuntimeException("OneEntry application has not been initialized")

        client.request(link + parameters.query, block)
    }
}