package com.example.oneentry.network

import com.example.oneentry.model.OneEntryAdmin
import com.example.oneentry.model.OneEntryBlock
import com.example.oneentry.model.OneEntryGeneralType
import com.example.oneentry.model.OneEntryLocale
import com.example.oneentry.model.OneEntryMenu
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

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

        return core.requestItems("locales/active/all").body()
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

        return core.requestItems("general-types").body()
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

        val response = core.requestItems("blocks") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
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

        val response = core.requestItems("blocks/marker/$marker") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        println(response.bodyAsText())

        return response.body()
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

        val response = core.requestItems("admins") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get pages included in menu by marker
     *
     * @param marker Menu marker
     *
     * @return OneEntryMenu
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun menu(marker: String): OneEntryMenu {

        val response = core.requestItems("menus/marker/$marker")

        println(response.bodyAsText())

        return response.body()
    }
}