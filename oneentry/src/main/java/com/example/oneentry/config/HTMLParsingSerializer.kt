package com.example.oneentry.config

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jsoup.Jsoup

object HTMLParsingSerializer: KSerializer<String> {

    override val descriptor: SerialDescriptor = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: String) {

        encoder.encodeString(value)
    }

    override fun deserialize(decoder: Decoder): String {

        val html = decoder.decodeString()
        val doc = Jsoup.parse(html)

        return doc.text()
    }
}