package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsFilter(
    val attributeMarker: String,
    val conditionMarker: String,
    val conditionValue: Int,
    val pageId: Int
)
