package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class LocalizeInfo(
    val title: String?,
    val content: InfoContent?,
    val menuTitle: String?,
    val htmlContent: String?,
    val plainContent: String?
)

@Serializable
data class InfoContent(
    val value: String,
    val isEditorDisabled: Boolean
)
