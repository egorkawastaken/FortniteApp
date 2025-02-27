package main.common.network.status

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StatusSerializer : KSerializer<Status> {
    override val descriptor = PrimitiveSerialDescriptor("Status", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Status) {
        encoder.encodeInt(value.code) // Сериализуем как code (например, 200 для SUCCESS)
    }

    override fun deserialize(decoder: Decoder): Status {
        val code = decoder.decodeInt() // Читаем Int из JSON
        return Status.fromCode(code)
            ?: throw IllegalArgumentException("Unknown status code: $code")
    }
}