package com.example.oneentry.network.core

import com.example.oneentry.model.OneEntryException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun ParametersBuilder.append(name: String, value: Any?) {

    val textValue = value?.toString() ?: return

    append(name, textValue)
}

class OneEntryCore private constructor() {

    private var client: HttpClient? = null
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

            instance.client = credential.client.config {

                expectSuccess = true

                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = domain
                        path("api/content/")
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

    internal suspend fun requestItems(
        link: String,
        block: HttpRequestBuilder.() -> Unit = defaultRequestBuilder
    ): HttpResponse {

        val client = client ?: throw RuntimeException("OneEntry application has not been initialized")

        return client.request(link, block)
    }
}