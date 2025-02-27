package main.common.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DefaultContextProvider : ContextProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
    override val default: CoroutineContext by lazy { Dispatchers.Default }
}