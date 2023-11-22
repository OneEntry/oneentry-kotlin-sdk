package com.example.oneentry.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * OneEntry entity attribute
 *
 * @param type Type of attribute
 * @param value Value of attribute
 */
@Serializable
data class AttributeModel(
    var type: AttributeType,
    var value: JsonElement?
) {

    init {

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val serializer = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

        when (type) {

            AttributeType.real, AttributeType.float, AttributeType.string, AttributeType.date,
                AttributeType.time, AttributeType.integer -> value.toString()

            AttributeType.text -> serializer.decodeFromString<List<OneEntryText>>(value.toString())

            AttributeType.dateTime -> {

                val model = serializer.decodeFromString<Map<String, String>>(value.toString())
                val stringDate = model["date"] ?: ""
                val stringTime = model["time"] ?: ""

                val date = dateFormatter.parse(stringDate)
                val time = dateFormatter.parse(stringTime)

                if (date == null || time == null) {
                    value = null
                } else {

                    val calendar = Calendar.getInstance()
                    val dateComponents = calendar.get(Calendar.DAY_OF_MONTH)
                    val monthComponents = calendar.get(Calendar.MONTH)
                    val yearComponents = calendar.get(Calendar.YEAR)
                    val hourComponents = calendar.get(Calendar.HOUR_OF_DAY)
                    val minuteComponents = calendar.get(Calendar.MINUTE)
                    val secondComponents = calendar.get(Calendar.SECOND)

                    val newComponents = calendar.apply {
                        timeZone = calendar.timeZone
                        set(Calendar.DAY_OF_MONTH, dateComponents)
                        set(Calendar.MONTH, monthComponents)
                        set(Calendar.YEAR, yearComponents)
                        set(Calendar.HOUR_OF_DAY, hourComponents)
                        set(Calendar.MINUTE, minuteComponents)
                        set(Calendar.SECOND, secondComponents)
                    }

                    newComponents.time
                }
            }

            else -> value = null
        }
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