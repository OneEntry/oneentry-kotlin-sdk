package com.example.oneentry.model

import com.example.oneentry.config.HTMLParsingSerializer
import kotlinx.serialization.Serializable

/**
 * Object of text
 *
 * @param htmlValue String value
 * @param plainValue The plain text value associated with the object
 */
@Serializable
data class OneEntryText(
    @Serializable(with = HTMLParsingSerializer::class)
    val htmlValue: String,
    val plainValue: String
)