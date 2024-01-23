package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry image model
 *
 * @param size Image size
 * @param filename Full path to image
 * @param previewLink Preview link
 * @param downloadLink Link to download the image
 */
@Serializable
data class OneEntryImage(
    val size: Int,
    val filename: String,
    val previewLink: String,
    val downloadLink: String
)