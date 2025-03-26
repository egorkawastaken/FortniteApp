package main.common.extension

import androidx.lifecycle.SavedStateHandle

inline fun <reified T> SavedStateHandle.getOrThrow(key: String): T {
    return get<T>(key) ?: error("Missing required argument: $key")
}