package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * @param id The unique identifier for the object
 * @param index The unique identifier for the object
 * @param header The header information for the object
 * @param htmlValue The HTML value associated with the object
 * @param plainValue The plain text value associated with the object
 */
@Serializable
data class OneEntryTextWithHeader(
    val id: String,
    val index: String,
    val header: String,
    val htmlValue: String,
    val plainValue: String
)
