package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Represents a file associated with a single entry
 *
 * @param filename The full path to the file
 * @param downloadLink The download link or URL for accessing the file
 * @param size The size of the file in bytes
 */
@Serializable
data class OneEntryFile(
    val filename: String,
    val downloadLink: String,
    val size: Int
)
