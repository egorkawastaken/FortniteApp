package main.common.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.cancellation.CancellationException

interface BaseScopeContainer {
    val processScope: CoroutineScope
    val mainScope: CoroutineScope
    val ioScope: CoroutineScope

    /** Глобальный обработчик ошибок */
    val exceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable -> handleException(throwable) }

    fun launch(
        scope: CoroutineScope = mainScope,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onError: (suspend (Throwable) -> Unit)? = null,
        onFinally: (suspend () -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(
            context = context + exceptionHandler,
            start = start
        ) {
            try {
                block(this)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                onError?.invoke(e)
            } finally {
                onFinally?.invoke()
            }
        }
    }

    fun <T> throwAsync(
        scope: CoroutineScope = mainScope,
        context: CoroutineContext = EmptyCoroutineContext,
        onError: (suspend (Throwable) -> Unit)? = null,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return scope.async(
            context = context + exceptionHandler,
            start = start
        ) {
            try {
                block(this)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                onError?.invoke(e)
                throw e // Пробрасываем дальше, чтобы вызвать обработку в exceptionHandler
            }
        }
    }

    fun <T> async(
        scope: CoroutineScope = mainScope,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onError: (suspend (Throwable) -> T)? = null,
        onFinally: (suspend () -> Unit)? = null,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T?> {
        return scope.async(
            context = context + exceptionHandler,
            start = start
        ) {
            try {
                block(this)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                onError?.invoke(e)
                null // Не пробрасываем ошибку, просто возвращаем null
            } finally {
                onFinally?.invoke()
            }
        }
    }

    suspend fun <T> withMain(action: suspend CoroutineScope.() -> T) =
        withContext(mainScope.coroutineContext + exceptionHandler, action)

    suspend fun <T> withIO(action: suspend CoroutineScope.() -> T) =
        withContext(ioScope.coroutineContext + exceptionHandler, action)

    suspend fun <T> tryRun(
        defaultValue: T? = null,
        onError: (suspend (Throwable) -> T)? = null,
        block: suspend () -> T
    ): T? {
        return try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            onError?.invoke(e)
            defaultValue
        }
    }

    fun handleException(throwable: Throwable) {
        Log.e("BaseScopeContainer", "Unhandled exception", throwable)
    }
}