package com.example.oneentry.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.serialization.gson.gson
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
    internal val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson(ContentType("application", "json;charset=utf-8"))
        }
    }
    internal val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
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
            this.method = method
            setBody(body)
        }

        return serializer.decodeFromString(response.bodyAsText())
    }
}

