package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryTextWithHeader(
    val header: String
)
