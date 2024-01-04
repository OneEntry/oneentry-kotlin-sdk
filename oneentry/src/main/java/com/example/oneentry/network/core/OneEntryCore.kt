package com.example.oneentry.network.core

import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
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
    val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    companion object {

        val instance: OneEntryCore = OneEntryCore()
        var credential = OneEntryCredential.instance
        private lateinit var headers: HeadersBuilder

        fun initializeApp(domain: String, token: String) {

            instance.domain = domain
            headers = credential.credentialToken(token)
            credential.generateHttpClient()
        }

        fun initializeApp(domain: String, certificateName: String, password: String, filePath: String) {

            instance.domain = domain
            headers = credential.credentialAuth(certificateName, password, filePath)
            credential.generateHttpClient()
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
        val headers = headers.build()

        val response = credential.client.request(url) {
            this.method = method
            this.headers.appendAll(headers)
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
        val headers = headers.build()

        val response = credential.client.request(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
            this.method = method
            this.headers.appendAll(headers)
        }

        return serializer.decodeFromString(response.bodyAsText())
    }

    internal suspend fun requestData(
        link: String,
        parameters: Map<String, Any?> = mapOf(),
        method: HttpMethod = HttpMethod.Get
    ) {

        if (domain == null)
            throw RuntimeException("OneEntry application has not been initialized")

        val url = domain + api + link + parameters.query
        val headers = headers.build()

        credential.client.request(url) {
            this.method = method
            this.headers.appendAll(headers)
        }
    }
}