package com.example.oneentry.network

import com.example.oneentry.model.OneEntryTemplatePreview

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
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun templates(): List<OneEntryTemplatePreview> {

        return core.requestItems("/template-previews")
    }

    /**
     * Get one template object
     *
     * @param id Template object identifier
     *
     * @return OneEntryTemplatePreview
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun template(id: Int): OneEntryTemplatePreview {

        return core.requestItems("/template-previews/$id")
    }

    /**
     * Get one template object by marker
     *
     * @param marker Template object marker
     *
     * @return OneEntryTemplatePreview
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun template(marker: String): OneEntryTemplatePreview {

        return core.requestItems("/template-previews/marker/$marker")
    }
}