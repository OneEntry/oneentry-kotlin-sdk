package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * Represents a template preview for a single entry, with support for localization
 *
 * @param id Unique identifier for the preview
 * @param version Version of the template, if available
 * @param identifier Identifier for the template
 * @param localizeInfos Localization information organized as a dictionary of LocalizeInfo objects, if available
 */
@Serializable
data class OneEntryTemplatePreview(
    val id: Int,
    val version: Int,
    val identifier: String,
    val localizeInfos: Map<String, LocalizeInfo>?
)

/**
 * Represents the proportion information for a single entry, including width, height, side, marker, and alignment type
 *
 * @param width The width of the proportion
 * @param height The height of the proportion
 * @param side The side of the proportion
 * @param marker The marker associated with the proportion
 * @param alignmentType The type of alignment for the proportion
 */
@Serializable
data class OneEntryProportion(
    val width: Double?,
    val height: Double?,
    val side: Double?,
    val marker: String,
    val alignmentType: String
)
