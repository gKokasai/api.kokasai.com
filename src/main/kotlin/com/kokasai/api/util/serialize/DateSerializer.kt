package com.kokasai.api.util.serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.Date

class DateSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor by lazy {
        PrimitiveSerialDescriptor(
            DateSerializer::class.qualifiedName!!,
            PrimitiveKind.STRING
        )
    }

    private val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(formatter.format(value))
    }

    override fun deserialize(decoder: Decoder): Date {
        return formatter.parse(decoder.decodeString())
    }
}
