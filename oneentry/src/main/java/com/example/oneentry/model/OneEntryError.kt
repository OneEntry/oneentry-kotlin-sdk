package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryError(
    val statusCode: Int,
    val message: String,
    val error: String?
)
