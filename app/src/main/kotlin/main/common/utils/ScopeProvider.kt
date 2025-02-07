package main.common.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.Closeable

open class ScopeProvider (
    private val contextProvider: ContextProvider
): Closeable {

    @Volatile
    private var job = SupervisorJob()

    val isCancelled: Boolean
        get() = job.isCancelled

    private val _mainScope = MutableStateFlow(CoroutineScope(contextProvider.main + job))
    private val _ioScope = MutableStateFlow(CoroutineScope(contextProvider.io + job))

    val mainScope: CoroutineScope get() = _mainScope.value
    val ioScope: CoroutineScope get() = _ioScope.value

    /**
     * Проверяет, не отменён ли job, и создаёт новый scope при необходимости
     */
    fun restart() {
        if (!job.isCancelled) cancel()
        job = SupervisorJob()
        _mainScope.value = CoroutineScope(contextProvider.main + job)
        _ioScope.value = CoroutineScope(contextProvider.io + job)
    }

    override fun close() {
        cancel()
    }

    fun cancel() {
        if (!job.isCancelled) job.cancel()
    }
}