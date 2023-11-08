package com.example.oneentry.model

import kotlinx.serialization.Serializable

@Serializable
data class LocalizeInfo(
    val title: String? = null,
    val content: InfoContent? = null,
    val menuTitle: String? = null,
    val htmlContent: String? = null,
    val plainContent: String? = null
)

@Serializable
data class InfoContent(
    val value: String,
    val isEditorDisabled: Boolean
)
