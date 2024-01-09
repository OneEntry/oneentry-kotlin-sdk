package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry general type
 *
 * @param id Generale type id
 * @param type General type string value
 */
@Serializable
data class OneEntryGeneralType(
    val id: Int,
    val type: String
)