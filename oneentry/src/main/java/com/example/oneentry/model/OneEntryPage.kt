package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
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

/**
 * OneEntry page search dto
 *
 * @param id Page id
 * @param title Page title
 */
@Serializable
data class OneEntrySearchPage(
    val id: Int,
    val title: String
)
