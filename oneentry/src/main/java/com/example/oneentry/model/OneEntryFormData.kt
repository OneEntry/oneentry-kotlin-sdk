package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class OneEntryFormDataRequest(
    val formIdentifier: String,
    val formData: Map<String, List<OneEntryFormData>>
)

@Serializable
data class OneEntryFormData(
    val marker: String,
    val value: String
)

@Serializable
data class OneEntryFormDataResponse(
    val id: Int,
    val time: String,
    val formIdentifier: String,
    val formData: Map<String, List<OneEntryFormData>>
)
