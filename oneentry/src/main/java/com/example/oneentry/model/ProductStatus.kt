package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductStatus(
    val id: Int,
    val updatedDate: String,
    val version: Int,
    val identifier: String,
    val localizeInfos: Map<String, LocalizeInfo>
)
