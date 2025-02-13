package main.common.utils.status

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class StatusAdapter : JsonDeserializer<Status>, JsonSerializer<Status> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Status? {
        return if (json.isJsonNull) {
            null
        } else {
            Status.fromCode(json.asInt)
        }
    }

    override fun serialize(src: Status?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return if (src == null) {
            JsonNull.INSTANCE
        } else {
            JsonPrimitive(src.code)
        }
    }
}