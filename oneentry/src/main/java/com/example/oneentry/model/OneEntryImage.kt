package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry image model
 *
 * @param size Image size
 * @param downloadLink Link to download the image
 */
@Serializable
data class OneEntryImage(
    val size: Int,
    val downloadLink: String
)