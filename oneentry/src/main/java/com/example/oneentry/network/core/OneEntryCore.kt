package com.example.oneentry.network.core

import com.example.oneentry.model.OneEntryException
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
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
    internal var token: String? = null
    internal val api = "/api/content"
    internal val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    internal val client = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(serializer)
        }
        HttpResponseValidator {

            handleResponseExceptionWithRequest { exception, _ ->

                val responseException = exception as? ResponseException ?: return@handleResponseExceptionWithRequest
                val response = responseException.response
                val oneEntryException = serializer.decodeFromString<OneEntryException>(response.bodyAsText())

                throw oneEntryException
            }
        }
    }

    companion object {

        val instance: OneEntryCore = OneEntryCore()
        fun initializeApp(domain: String, token: String) {

            instance.domain = domain
            instance.token = token
        }
    }

    internal suspend inline fun <reified T>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod = HttpMethod.Get
    ): T {

        if (domain == null || token == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query

        val response = client.request(url) {
            this.method = method
            this.headers {
                append("x-app-token", token!!)
            }
        }

        return serializer.decodeFromString(response.bodyAsText())
    }

    internal suspend inline fun <reified T, reified G>requestItems(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod,
        body: G
    ): T {

        if (domain == null || token == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query

        val response = client.request(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            this.method = method
            this.headers {
                append("x-app-token", token!!)
            }
        }

        return serializer.decodeFromString(response.bodyAsText())
    }

    internal suspend fun requestData(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod = HttpMethod.Get
    ) {

        if (domain == null || token == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query

        client.request(url) {
            this.method = method
            this.headers {
                append("x-app-token", token!!)
            }
        }
    }
}