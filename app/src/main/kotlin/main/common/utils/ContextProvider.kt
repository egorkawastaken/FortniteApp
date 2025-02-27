package main.common.utils

import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}