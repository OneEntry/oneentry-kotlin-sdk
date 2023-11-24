package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry products filter
 *
 * @param attributeMarker Attribute marker to be filtered
 * @param conditionMarker Filtration condition
 * @param conditionValue Value of the attribute for filtering
 * @param pageId Page id
 */
@Serializable
data class ProductsFilter(
    val attributeMarker: String,
    val conditionMarker: String,
    val conditionValue: Int,
    val pageId: Int
)
