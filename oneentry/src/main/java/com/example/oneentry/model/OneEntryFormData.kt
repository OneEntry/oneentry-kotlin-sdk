package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Represents a request containing form data for a single entry
 *
 * @param formIdentifier Identifier for the form
 * @param formData Form data organized as a dictionary of arrays of OneEntryFormData
 */
@Serializable
data class OneEntryFormDataRequest(
    val formIdentifier: String,
    val formData: Map<String, List<OneEntryFormData>>
)

/**
 * Represents a single entry form data
 *
 * @param marker Marker for the form data
 * @param value Value associated with the marker
 */
@Serializable
data class OneEntryFormData(
    val marker: String,
    val value: String
)

@Serializable
data class OneEntryFormDataResponse(
    val items: List<OneEntryFormDataResponseBody>? = null,
    val total: Int? = null
)

/**
 * Represents a response containing form data for a single entry with an identity and time
 *
 * @param id Unique identifier for the form data response
 * @param time Time when the form data was submitted
 * @param formIdentifier Identifier for the form
 * @param formData Form data organized as a dictionary of arrays of OneEntryFormData
 */
@Serializable
data class OneEntryFormDataResponseBody(
    val id: Int,
    val time: String,
    val formIdentifier: String,
    val formData: Map<String, List<OneEntryFormData>>
)
