package com.example.oneentry.network

import com.example.oneentry.model.OneEntryAdmin
import com.example.oneentry.model.OneEntryBlock
import com.example.oneentry.model.OneEntryGeneralType
import com.example.oneentry.model.OneEntryLocale

class OneEntryProject private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryProject = OneEntryProject()
    }

    /**
     * Find all active language localization objects (available for use)
     *
     * @return List<Locale>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun locales(): List<OneEntryLocale> {

        return core.requestItems("/locales/active/all")
    }

    /**
     * Get all types
     *
     * @return List<OneEntryGeneralType>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun generalTypes(): List<OneEntryGeneralType> {

        return core.requestItems("/general-types")
    }

    /**
     * Get all block objects
     *
     * @param offset parameter for pagination, default is 0
     * @param limit parameter for pagination, default is 30
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<OneEntryBlock>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun blocks(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String
    ): List<OneEntryBlock> {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode
        )

        return core.requestItems("/blocks", parameters)
    }

    /**
     * Get a block object by marker
     *
     * @param marker Marker of the block object
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return OneEntryBlock
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun block(
        marker: String,
        langCode: String
    ): OneEntryBlock {

        val parameters: Map<String, Any?> = mapOf(
            "langCode" to langCode
        )

        return core.requestItems("/blocks/marker/$marker", parameters)
    }

    /**
     * Get all user objects - admins
     *
     * @param offset parameter for pagination, default is 0
     * @param limit parameter for pagination, default is 30
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<OneEntryAdmins>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun admins(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String
    ): List<OneEntryAdmin> {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode
        )

        return core.requestItems("/admins", parameters)
    }
}