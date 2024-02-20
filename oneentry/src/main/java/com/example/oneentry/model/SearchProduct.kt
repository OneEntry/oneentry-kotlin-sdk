package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry search products
 *
 * @param id Product Id
 * @param title Product title
 * @param pageId Id of the page to which the product is linked
 */
@Serializable
data class SearchProduct(
    val id: Int,
    val title: String,
    val pageId: Int
)
