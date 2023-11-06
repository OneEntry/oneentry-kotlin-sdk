package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchProduct(
    val id: Int,
    val title: String,
    val pageId: Int
)
