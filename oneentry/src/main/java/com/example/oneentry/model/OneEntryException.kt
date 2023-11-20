package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryException(
    val statusCode: Int,
    override val message: String,
    val error: String? = null
): Exception()
