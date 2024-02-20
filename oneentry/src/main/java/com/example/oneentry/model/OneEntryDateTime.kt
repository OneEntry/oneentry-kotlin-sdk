package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryDateTime(
    val date: String,
    val time: String
)