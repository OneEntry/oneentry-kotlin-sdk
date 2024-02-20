package com.example.oneentry.network

import com.example.oneentry.model.OneEntryAllTemplates
import com.example.oneentry.model.OneEntryTemplate
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.model.OneEntryException
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

class OneEntryTemplates private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryTemplates = OneEntryTemplates()
    }

    /**
     * Get all template objects of a specific type
     *
     * @param type Template type name
     *
     * Type variant:
     * - forCatalogProducts
     * - forBasketPage
     * - forErrorPage
     * - forCatalogPages
     * - forProductPreview
     * - forProductPage
     * - forSimilarProductBlock
     * - forStatisticProductBlock
     * - forProductBlock
     * - forForm
     * - forFormField
     * - forNewsPage
     * - forNewsBlock
     * - forNewsPreview
     * - forOneNewsPage
     * - forUsualPage
     * - forTextBlock
     * - forSlider
     * - service
     *
     * @return List<OneEntryTemplate>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun templates(type: String): List<OneEntryTemplate> {

        val response = core.requestItems("templates") {
            url {
                parameters.append("type", type)
            }
        }

        return response.body()
    }

    /**
     * Get all template objects grouped by types
     *
     * @return OneEntryAllTemplates
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun templates(): OneEntryAllTemplates {

        val response = core.requestItems("templates/all")
        val templateDictionary = core.serializer.decodeFromString<Map<String, List<OneEntryTemplate>>>(response.bodyAsText())

        return OneEntryAllTemplates(templateDictionary)
    }
}