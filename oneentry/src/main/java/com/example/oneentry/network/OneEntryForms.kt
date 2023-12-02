package com.example.oneentry.network

import com.example.oneentry.model.OneEntryForm
import com.example.oneentry.model.OneEntryFormData
import com.example.oneentry.model.OneEntryFormDataRequest
import com.example.oneentry.model.OneEntryFormDataResponse
import io.ktor.http.HttpMethod

class OneEntryForms private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryForms = OneEntryForms()
    }

    suspend fun forms(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String
    ): List<OneEntryForm> {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode
        )

        return core.requestItems("/forms", parameters)
    }

    suspend fun form(
        marker: String,
        langCode: String
    ): OneEntryForm {

        val parameters: Map<String, Any?> = mapOf(
            "langCode" to langCode
        )

        return core.requestItems("/forms/marker/$marker", parameters)
    }

    suspend fun sendData(
        identifier: String,
        data: Map<String, List<OneEntryFormData>>
    ): OneEntryFormDataResponse {

        val body = OneEntryFormDataRequest(identifier, data)

        return core.requestItems(link = "/form-data", method = HttpMethod.Post, body = body)
    }

    suspend fun data(): List<OneEntryFormDataResponse> {

        return core.requestItems("form-data")
    }

    suspend fun data(
        marker: String,
        offset: Int = 0,
        limit: Int = 30
    ): OneEntryFormDataResponse {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit
        )

        return core.requestItems("/form-data/marker/$marker", parameters)
    }
}