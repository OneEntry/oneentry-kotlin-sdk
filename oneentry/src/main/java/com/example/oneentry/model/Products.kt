package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)

/**
 * OneEntry catalog products
 *
 * @param id Product Id
 * @param localizeInfos Product localize info
 * @param price Product price
 * @param attributeValues Product attributes
 */
@Serializable
data class ProductModel(
    val id: Int,
    val localizeInfos: Map<String, LocalizeInfo>,
    val price: Double?,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)



