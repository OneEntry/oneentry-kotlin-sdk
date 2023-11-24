package com.example.oneentry.model

import com.example.oneentry.config.HTMLParsingSerializer
import kotlinx.serialization.Serializable

/**
 * Object of text
 *
 * @param value String value
 */
@Serializable
data class OneEntryText(
    @Serializable(with = HTMLParsingSerializer::class)
    val htmlValue: String
)
