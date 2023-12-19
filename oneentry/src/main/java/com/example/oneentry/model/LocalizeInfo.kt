package com.example.oneentry.model

import com.example.oneentry.network.OneEntryCore.Companion.instance
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Localized information about the object
 *
 * @param title Object title
 * @param content Object content
 * @param menuTitle Object menu title
 * @param htmlContent Object content as html text
 * @param plainContent Object content as markdown text
 */
@Serializable
data class LocalizeInfo(
    val title: String? = null,
    private val content: JsonElement? = null,
    val menuTitle: String? = null,
    val htmlContent: String? = null,
    val plainContent: String? = null
) {

    val contentAsLocalizeInfo: InfoContent?
        get() = instance.serializer.decodeFromJsonElementOrNull(content)
}

/**
 * Object content
 *
 * @param value Value of object content
 * @param isEditorDisabled Whether the content is editable
 */
@Serializable
data class InfoContent(
    val value: String,
    val isEditorDisabled: Boolean
)
