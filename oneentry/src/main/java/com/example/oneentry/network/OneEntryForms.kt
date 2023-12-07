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

    /**
     * Get all form objects
     *
     * @param offset Parameter for offsetting the selection of records, default - 0
     * @param limit Parameter for limiting the number of records, default - 30
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<OneEntryForm>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
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

    /**
     * Get a single form object by marker
     *
     * @param marker marker of the form object. Example: my-form
     * @param langCode language code
     *
     * @return OneEntryForm
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun form(
        marker: String,
        langCode: String
    ): OneEntryForm {

        val parameters: Map<String, Any?> = mapOf(
            "langCode" to langCode
        )

        return core.requestItems("/forms/marker/$marker", parameters)
    }

    /**
     * Create form data info object
     *
     * @param identifier form marker
     * @param data form data to send
     *
     * @return OneEntryFormDataResponse
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun sendData(
        identifier: String,
        data: Map<String, List<OneEntryFormData>>
    ): OneEntryFormDataResponse {

        val body = OneEntryFormDataRequest(identifier, data)

        return core.requestItems(link = "/form-data", method = HttpMethod.Post, body = body)
    }

    /**
     * Find all form data
     *
     * @return List<OneEntryFormDataResponse>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun data(): List<OneEntryFormDataResponse> {

        return core.requestItems("form-data")
    }

    /**
     * Find form data by text identifier (marker)
     *
     * @param marker Text identifier of the form
     * @param offset Parameter for pagination
     * @param limit Parameter for pagination
     *
     * @return OneEntryFormDataResponse
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
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