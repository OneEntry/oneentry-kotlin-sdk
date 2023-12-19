package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry form model
 *
 * @param id Form id
 * @param localizeInfos Form localize info
 * @param attributeValues Form attributes
 * @param version Form status version number
 * @param identifier Form status marker
 */
@Serializable
data class OneEntryForm(
    val id: Int,
    val localizeInfos: Map<String, LocalizeInfo>?,
    val attributeValues: Map<String, Map<String, AttributeModel>>? = null,
    val version: Int,
    val identifier: String,
    val processingType: String?
)
