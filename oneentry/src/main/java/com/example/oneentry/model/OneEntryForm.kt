package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryForm(
    val id: Int,
    val localizeInfos: Map<String, LocalizeInfo>?,
    val attributeValues: Map<String, Map<String, AttributeModel>>?,
    val version: Int,
    val identifier: String,
    val processingType: String?
)
