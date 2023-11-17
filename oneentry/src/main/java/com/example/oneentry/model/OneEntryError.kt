package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryError(
    val statusCode: Int,
    override val message: String,
    val error: String? = null
): Exception()
