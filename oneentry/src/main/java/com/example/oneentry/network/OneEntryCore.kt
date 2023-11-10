package com.example.oneentry.network

import com.example.oneentry.model.OneEntryError
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.decodeFromString
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

    internal var domain: String? = null
    internal val api = "/api/content"
    internal val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    internal val client = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys
                }
            )
        }
        HttpResponseValidator {

            handleResponseExceptionWithRequest { exception, _ ->

                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response

                if (exceptionResponse.status != HttpStatusCode.OK) {

                    val exceptionResponseText = serializer.decodeFromString<OneEntryError>(exceptionResponse.bodyAsText())
                    throw Exception("Message: ${exceptionResponseText.message}")
                }
            }
        }
    }

    companion object {

        val instance: OneEntryCore = OneEntryCore()
        fun initializeApp(domain: String) {

            instance.domain = domain
        }
    }

    internal suspend inline fun <reified T>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod = HttpMethod.Get
    ): T {

        if (domain == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query

        val response = client.request(url) {
            this.method = method
        }

        return serializer.decodeFromString(response.bodyAsText())
    }

    internal suspend inline fun <reified T, reified G>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod,
        body: G
    ): T {

        if (domain == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query

        val response = client.request(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            this.method = method
        }

        return serializer.decodeFromString(response.bodyAsText())
    }
}