package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val attributeMarker: String,
    val conditionMarker: String,
    val conditionValue: String,
    val pageId: Int
)
