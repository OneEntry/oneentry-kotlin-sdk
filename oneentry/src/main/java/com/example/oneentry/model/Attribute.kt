package com.example.oneentry.model

import com.example.oneentry.network.OneEntryCore.Companion.instance
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

    val asString: String?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asText: List<OneEntryText>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asImage: List<OneEntryImage>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asDateTime: OneEntryDateTime?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)

    val asTextWithHeader: List<OneEntryTextWithHeader>?
        get() = instance.serializer.decodeFromJsonElementOrNull(value)
}

inline fun <reified T> Json.decodeFromJsonElementOrNull(json: JsonElement): T? {
    return try {
        decodeFromJsonElement(json)
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
enum class AttributeType {

    integer,
    date,
    file,
    list,
    real,
    spam,
    text,
    time,
    float,
    button,
    image,
    string,
    dateTime,
    textWithHeader,
    groupOfImages,
    radioButton
}