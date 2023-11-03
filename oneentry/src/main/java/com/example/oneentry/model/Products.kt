package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)

@Serializable
data class ProductModel(
    val id: Int,
    val localizeInfos: Map<String, LocalizeInfo>,
    val price: Double?,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)



