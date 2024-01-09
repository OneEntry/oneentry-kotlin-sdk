package com.example.oneentry.network

import com.example.oneentry.model.OneEntryTemplatePreview
import com.example.oneentry.network.core.OneEntryCore
import io.ktor.client.call.body
import com.example.oneentry.model.OneEntryException

class OneEntryTemplatePreviews private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryTemplatePreviews = OneEntryTemplatePreviews()
    }

    /**
     * Get all template objects
     *
     * @return List<OneEntryTemplatePreview>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun templates(): List<OneEntryTemplatePreview> {

        return core.requestItems("template-previews").body()
    }

    /**
     * Get one template object
     *
     * @param id Template object identifier
     *
     * @return OneEntryTemplatePreview
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun template(id: Int): OneEntryTemplatePreview {

        return core.requestItems("template-previews/$id").body()
    }

    /**
     * Get one template object by marker
     *
     * @param marker Template object marker
     *
     * @return OneEntryTemplatePreview
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun template(marker: String): OneEntryTemplatePreview {

        return core.requestItems("template-previews/marker/$marker").body()
    }
}