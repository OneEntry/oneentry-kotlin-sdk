package com.example.oneentry.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AttributeModel(
    val type: AttributeType,
    val value: JsonElement?
)

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