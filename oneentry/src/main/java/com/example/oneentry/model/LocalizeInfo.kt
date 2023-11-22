package com.example.oneentry.model

import com.example.oneentry.network.OneEntryCore.Companion.instance
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class LocalizeInfo(
    val title: String? = null,
    private val content: JsonElement,
    val menuTitle: String? = null,
    val htmlContent: String? = null,
    val plainContent: String? = null
) {

    val contentAsLocalizeInfo: InfoContent?
        get() = instance.serializer.decodeFromJsonElementOrNull(content)
}

inline fun <reified T> Json.decodeFromJsonElementOrNull(json: JsonElement): T? {
    return try {
        decodeFromJsonElement(json)
    } catch (e: Exception) {
        null
    }
}

@Serializable
data class InfoContent(
    val value: String,
    val isEditorDisabled: Boolean
)
