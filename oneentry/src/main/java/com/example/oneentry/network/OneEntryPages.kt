package com.example.oneentry.network

import com.example.oneentry.model.OneEntryPage
import com.example.oneentry.model.PageConfig
import com.example.oneentry.model.OneEntrySearchPage
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body

class OneEntryPages private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryPages = OneEntryPages()
    }

    /**
     * Get all top-level page objects
     *
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun rootPages(langCode: String): List<OneEntryPage> {

        val response = core.requestItems("pages/root") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get all page objects with products information
     *
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     * @param limit parameter for pagination, default is 30
     * @param offset parameter for pagination, default is 0
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun catalogPages(
        langCode: String,
        limit: Int? = null,
        offset: Int? = null
    ): List<OneEntryPage> {

        val response = core.requestItems("pages/catalog") {
            url {
                parameters.append("langCode", langCode)
                parameters.append("limit", limit)
                parameters.append("offset", offset)
            }
        }

        return response.body()
    }

    /**
     * Get all page objects with products information
     *
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun pages(langCode: String): List<OneEntryPage> {

        val response = core.requestItems("pages") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get one page object with information about forms, blocks, menus, linked to the page
     *
     * @param id Page object identifier
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun page(
        id: Int,
        langCode: String
    ): OneEntryPage {

        val response = core.requestItems("pages/$id") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get child pages with product information as an array.
     *
     * @param url Page URL
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun pagesChildren(
        url: String,
        langCode: String
    ): List<OneEntryPage> {

        val response = core.requestItems("pages/$url/children") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get one page object with information about forms, blocks, menus, linked to the page
     *
     * @param url Page URL
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun page(
        url: String,
        langCode: String
    ): OneEntryPage {

        val response = core.requestItems("pages/url/$url") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get ContentPageFormDto objects for the related form by URL
     *
     * @param url Page URL
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun pagesForms(
        url: String,
        langCode: String
    ): List<OneEntryPage> {

        val response = core.requestItems("pages/$url/forms") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get ContentPageBlockDto objects for the related block by URL
     *
     * @param url Page URL
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun pagesBlocks(
        url: String,
        langCode: String
    ): List<OneEntryPage> {

        val response = core.requestItems("pages/$url/blocks") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Get settings for the page
     *
     * @param url Page URL
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun config(url: String): OneEntryPageConfig {

        return core.requestItems("pages/$url/config").body()
    }

    /**
     * Quick search for page objects with limited output
     *
     * @param name text for searching page objects (search is performed on the title field of the localizeInfos object with the language taken into account)
     * @param langCode locale code (used only when searching with a filter (default - en_US))
     *
     * @return List<Page>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryError in case of OneEntry errors
     */
    suspend fun quickSearch(
        name: String,
        langCode: String
    ): List<OneEntrySearchPage> {

        val response = core.requestItems("pages/quick/search") {
            url {
                parameters.append("name", name)
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }
}