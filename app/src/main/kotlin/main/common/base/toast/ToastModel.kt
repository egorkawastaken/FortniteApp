package main.common.base.toast

data class ToastModel(
    val message: String,
    val type: ToastType,
    val duration: Long
) {
    enum class ToastType {
        SUCCESS, NOTIFICATION, ERROR
    }
}