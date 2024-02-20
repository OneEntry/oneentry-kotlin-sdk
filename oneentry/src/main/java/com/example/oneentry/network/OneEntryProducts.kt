package com.example.oneentry.network

import com.example.oneentry.model.ConditionMarker
import com.example.oneentry.model.ProductModel
import com.example.oneentry.model.ProductStatus
import com.example.oneentry.model.ProductsFilter
import com.example.oneentry.model.ProductsResult
import com.example.oneentry.model.SearchProduct
import com.example.oneentry.model.SortDirection
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import com.example.oneentry.model.OneEntryException

class OneEntryProducts private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryProducts = OneEntryProducts()
    }

    /**
     * Search for all products page object with pagination and filtering
     *
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param statusMarker identifier of the product page status (default not set).
     * @param conditionValue value that is being searched (default not set).
     * @param conditionMarker identifier of the filter condition by which values are filtered (default not set).
     * @param attributeMarker text identifier of the indexed attribute by which values are filtered (default not set).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun products(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        statusMarker: String? = null,
        conditionValue: String? = null,
        conditionMarker: ConditionMarker? = null,
        attributeMarker: String? = null,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("statusMarker", statusMarker)
                parameters.append("conditionValue", conditionValue)
                parameters.append("conditionMarker", conditionMarker)
                parameters.append("attributeMarker", attributeMarker)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
        }

        return response.body()
    }

    /**
     * Search for all product page objects with pagination that do not have a category
     *
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun emptyPageProducts(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products/empty-page") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
        }

        return response.body()
    }

    /**
     * Search for all product page objects with pagination for the selected category
     *
     * @param pageId page identifier - category.
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun products(
        pageId: Int,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products/page/$pageId") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
        }

        return response.body()
    }

    /**
     * Search for all product page objects with pagination for the selected category
     *
     * @param url URL of the category page.
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun products(
        url: String,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products/page/url/$url") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
        }

        return response.body()
    }

    /**
     * Find all related product page objects
     *
     * @param id product page identifier for which to find relationships.
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun relatedProducts(
        id: Int,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products/$id/related") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
        }

        return response.body()
    }

    /**
     * Retrieve one product object
     *
     * @param id product ID.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     *
     * @return ProductModel
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun products(
        id: Int,
        langCode: String
    ): ProductModel {

        val response = core.requestItems("products/$id") {
            url {
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Find all product page objects with pagination and multiple filtering
     *
     * @param body
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun filterProducts(
        body: List<ProductsFilter>,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val response = core.requestItems("products/conditions-filter") {
            method = HttpMethod.Post
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
                parameters.append("langCode", langCode)
                parameters.append("sortKey", sortKey)
                parameters.append("sortOrder", sortOrder)
            }
            setBody(body)
        }

        return response.body()
    }

    /**
     * Quick search for product page objects with limited output
     *
     * @param name
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     *
     * @return List<SearchProduct>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun quickSearch(
        name: String,
        langCode: String
    ): List<SearchProduct> {

        val response = core.requestItems("products/quick/search") {
            url {
                parameters.append("name", name)
                parameters.append("langCode", langCode)
            }
        }

        return response.body()
    }

    /**
     * Search for all product status objects
     *
     * @return List<ProductStatus>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun productStatuses(): List<ProductStatus> {

        return core.requestItems("product-statuses").body()
    }

    /**
     * Search for a product status object by identifier
     *
     * @param id Product status id
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun productStatus(id: Int): ProductStatus {

        return core.requestItems("product-statuses/$id").body()
    }

    /**
     * Search for a product status object by its textual identifier (marker)
     *
     * @param marker Textual identifier (marker)
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun productStatus(marker: String): ProductStatus {

        return core.requestItems("product-statuses/marker/$marker").body()
    }

    /**
     * Check the existence of a textual identifier (marker)
     *
     * @param marker Textual identifier (marker)
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun productStatusMarkerValidation(marker: String): Boolean {

        val response = core.requestItems("product-statuses/marker-validation/$marker")

        println(response.bodyAsText())

        return core.serializer.decodeFromString(response.bodyAsText())
    }
}