package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Page config
 *
 * @param rowsPerPage Rows value per page
 * @param productsPerRow Products value per row
 */
@Serializable
data class OneEntryPageConfig(
    val rowsPerPage: Int?,
    val productsPerRow: Int?
)
