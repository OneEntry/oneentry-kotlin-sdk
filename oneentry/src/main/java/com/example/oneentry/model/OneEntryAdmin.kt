package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Admin model
 *
 * @param id Admin id
 * @param identifier Admin marker
 * @param position Admin position
 */
@Serializable
data class OneEntryAdmin(
    val id: Int,
    val identifier: String,
    val position: Int?
)
