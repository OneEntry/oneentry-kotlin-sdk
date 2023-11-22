package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class PageConfig(
    val rowsPerPage: Int?,
    val productsPerRow: Int?
)
