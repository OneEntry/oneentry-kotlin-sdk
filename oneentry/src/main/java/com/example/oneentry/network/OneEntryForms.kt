package com.example.oneentry.network

import com.example.oneentry.model.OneEntryForm
import com.example.oneentry.model.OneEntryFormData
import com.example.oneentry.model.OneEntryFormDataRequest
import com.example.oneentry.model.OneEntryFormDataResponse
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
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

        val response = core.requestItems("forms") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
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

        val response = core.requestItems("forms/marker/$marker") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
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
        val response = core.requestItems("form-data") {
            url {
                method = HttpMethod.Post
                setBody(body)
            }
        }

        return response.body()
    }

    /**
     * Find all form data
     *
     * @return List<OneEntryFormDataResponse>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun data(): OneEntryFormDataResponse {

        return core.requestItems("form-data").body()
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

        val response = core.requestItems("form-data/marker/$marker") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
            }
        }

        return response.body()
    }
}