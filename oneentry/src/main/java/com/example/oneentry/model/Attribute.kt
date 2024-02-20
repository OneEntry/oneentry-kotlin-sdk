package com.example.oneentry.model

import com.example.oneentry.network.core.OneEntryCore.Companion.instance
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import java.util.Date

/**
 * OneEntry entity attribute
 *
 * @param type Type of attribute
 * @param value Value of attribute
 */
@Serializable
data class AttributeModel(
    var type: AttributeType,
    var value: JsonElement
) {

    val asInt: Int?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asDouble: Double?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asString: String?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asText: List<OneEntryText>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asImage: List<OneEntryImage>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asDate: Date?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asDateTime: OneEntryDateTime?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asTextWithHeader: List<OneEntryTextWithHeader>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asFile: List<OneEntryFile>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)
}

inline fun <reified T> Json.decodeFromJsonElementOrNull(json: JsonElement?): T? {
    return try {
        json?.let { decodeFromJsonElement(it) }
    } catch (e: Exception) {
        null
    }
}

/**
 * Type of attribute
 *
 * @param integer Integer attribute
 * @param date Date attribute without time
 * @param file File attribute
 * @param list List attribute
 * @param real Attribute with floating point
 * @param spam Spam attribute
 * @param text Text attribute
 * @param time Time attribute without date
 * @param float Attribute with floating point
 * @param button Button attribute
 * @param image Image attribute
 * @param string String attribute
 * @param dateTime Date & time attribute
 * @param textWithHeader Text attribute with header
 * @param groupOfImages Group of image attribute
 * @param radioButton Radio button attribute
 */
@Serializable
enum class AttributeType {

    @SerialName("integer")
    INTEGER,
    @SerialName("date")
    DATE,
    @SerialName("file")
    FILE,
    @SerialName("list")
    LIST,
    @SerialName("real")
    REAL,
    @SerialName("spam")
    SPAM,
    @SerialName("text")
    TEXT,
    @SerialName("time")
    TIME,
    @SerialName("float")
    FLOAT,
    @SerialName("button")
    BUTTON,
    @SerialName("image")
    IMAGE,
    @SerialName("string")
    STRING,
    @SerialName("dateTime")
    DATE_TIME,
    @SerialName("textWithHeader")
    TEXT_WITH_HEADER,
    @SerialName("groupOfImages")
    GROUP_OF_IMAGES,
    @SerialName("radioButton")
    RADIO_BUTTON
}