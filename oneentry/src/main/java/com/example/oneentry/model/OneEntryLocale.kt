package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Localization model
 *
 * @param id Locale id
 * @param shortCode Locale short code
 * @param code Locale full code
 * @param name Locale name
 * @param nativeName Locale native name
 * @param isActive Is locale active
 * @param image Locale icon
 * @param position Locale position
 */
@Serializable
data class OneEntryLocale(
    val id: Int,
    val shortCode: String,
    val code: String,
    val name: String,
    val nativeName: String,
    val isActive: Boolean,
    val image: String?,
    val position: Int?
)