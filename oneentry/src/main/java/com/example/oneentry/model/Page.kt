package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val id: Int,
    val parentId: Int?,
    val pageUrl: String,
    val depth: Int?,
    val isVisible: Boolean?,
    val position: Int? = null,
    val type: String?,
    val templateIdentifier: String?,
    val localizeInfos: Map<String, LocalizeInfo>?,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)

@Serializable
data class SearchPage(
    val id: Int,
    val title: String
)
