package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryFile(
    val filename: String,
    val downloadLink: String,
    val size: Int
)
