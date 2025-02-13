package main.common.utils.status

// Enum для статуса
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