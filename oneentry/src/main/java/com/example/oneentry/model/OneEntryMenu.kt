package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Structure of the OneEntry menu item
 *
 * @param id Menu id
 * @param identifier Menu marker
 * @param localizeInfos Menu localize info
 * @param pages Pages inside the menu item
 */
@Serializable
data class OneEntryMenu(
    val id: Int,
    val identifier: String,
    val localizeInfos: Map<String, LocalizeInfo>,
    val pages: List<OneEntryMenuPage>
)
