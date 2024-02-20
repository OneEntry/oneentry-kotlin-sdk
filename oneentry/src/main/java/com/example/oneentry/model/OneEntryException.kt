package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * The structure of errors that can come from OneEntry
 *
 * @param statusCode HTTP status error code
 * @param message OneEntry error message
 * @param error HTTP error
 */
@Serializable
data class OneEntryException(
    val statusCode: Int,
    override val message: String,
    val error: String? = null
): Exception()
