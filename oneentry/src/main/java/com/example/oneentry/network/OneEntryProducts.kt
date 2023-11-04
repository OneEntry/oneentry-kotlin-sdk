package com.example.oneentry.network

import com.example.oneentry.model.ConditionMarker
import com.example.oneentry.model.ProductStatus
import com.example.oneentry.model.ProductsFilter
import com.example.oneentry.model.ProductsResult
import com.example.oneentry.model.SortDirection
import io.ktor.http.HttpMethod

class OneEntryProducts {

    private val core = OneEntryCore.instance

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

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "statusMarker" to statusMarker,
            "conditionValue" to conditionValue,
            "conditionMarker" to conditionMarker,
            "attributeMarker" to attributeMarker,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products", parameters)
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
     */
    suspend fun emptyPageProducts(
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products/empty-page", parameters)
    }

    /**
     * Search for all product page objects with pagination for the selected category
     *
     * @param id page identifier - category.
     * @param offset parameter for pagination, default is 0.
     * @param limit parameter for pagination, default is 30.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     * @param sortKey field to sort by (default id).
     * @param sortOrder sorting order DESC | ASC (default DESC).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun products(
        id: Int,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products/page/$id", parameters)
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
     */
    suspend fun products(
        url: String,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products/page/url/$url", parameters)
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
     */
    suspend fun relatedProducts(
        id: Int,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products/$id/related", parameters)
    }

    /**
     * Retrieve one product object
     *
     * @param id product ID.
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun products(
        id: Int,
        langCode: String
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "langCode" to langCode
        )

        return core.requestItems("/products/$id", parameters)
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
     */
    suspend fun filterProducts(
        body: List<ProductsFilter>,
        offset: Int = 0,
        limit: Int = 30,
        langCode: String,
        sortKey: String? = null,
        sortOrder: SortDirection? = null
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "offset" to offset,
            "limit" to limit,
            "langCode" to langCode,
            "sortKey" to sortKey,
            "sortOrder" to sortOrder
        )

        return core.requestItems("/products/conditions-filter", parameters, HttpMethod.Post, body)
    }

    /**
     * Quick search for product page objects with limited output
     *
     * @param name
     * @param langCode locale code (used only when searching with a filter (default - en_US)).
     *
     * @return ProductsResult
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun quickSearch(
        name: String,
        langCode: String
    ): ProductsResult {

        val parameters: Map<String, Any?> = mapOf(
            "name" to name,
            "langCode" to langCode
        )

        return core.requestItems("/quick/search", parameters)
    }

    /**
     * Search for all product status objects
     *
     * @return List<ProductStatus>
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun productStatuses(): List<ProductStatus> {

        return core.requestItems("/product-statuses")
    }

    /**
     * Search for a product status object by identifier
     *
     * @param id Product status id
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun productStatus(id: Int): ProductStatus {

        return core.requestItems("/product-statuses/$id")
    }

    /**
     * Search for a product status object by its textual identifier (marker)
     *
     * @param marker Textual identifier (marker)
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun productStatus(marker: String): ProductStatus {

        return core.requestItems("/product-statuses/marker/$marker")
    }

    /**
     * Check the existence of a textual identifier (marker)
     *
     * @param marker Textual identifier (marker)
     *
     * @return ProductStatus
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     */
    suspend fun productStatusMarkerValidation(marker: String): Boolean {

        return core.requestItems("/product-statuses/marker-validation/$marker")
    }
}