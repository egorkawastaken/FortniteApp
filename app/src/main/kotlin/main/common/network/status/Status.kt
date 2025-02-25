package main.common.network.status

import kotlinx.serialization.Serializable

/** Enum для статуса */
@Serializable(with = StatusSerializer::class)
enum class Status(val code: Int) {
    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    SERVER_ERROR(500);

    companion object {
        fun fromCode(code: Int): Status? = entries.find { it.code == code }
    }
}