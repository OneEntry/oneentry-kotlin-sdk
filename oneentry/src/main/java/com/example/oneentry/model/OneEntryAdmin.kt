package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryAdmin(
    val id: Int,
    val identifier: String,
    val position: Int?
)
