package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Block information about the object
 *
 * @param id Block id
 * @param attributeSetId Identifier for the used attribute set
 * @param localizeInfos Block localize info
 * @param version Object version number
 * @param identifier Block status marker
 * @param attributeValues Block attributes
 */
@Serializable
data class OneEntryBlock(
    val id: Int,
    val attributeSetId: Int?,
    val localizeInfos: Map<String, LocalizeInfo>,
    val version: Int,
    val identifier: String,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)