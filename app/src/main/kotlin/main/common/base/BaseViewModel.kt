package main.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import main.common.utils.BaseScopeContainer
import main.common.utils.ScopeProvider

abstract class BaseViewModel<STATE : Any, ACTION : Any, EVENT : Any>(
    protected val scopeProvider: ScopeProvider
) : ViewModel(scopeProvider), BaseScopeContainer {

    final override val mainScope: CoroutineScope = scopeProvider.mainScope
    final override val ioScope: CoroutineScope = scopeProvider.ioScope

    init {
        subscribeToEvents()
    }

    private val _viewStates = MutableStateFlow<STATE?>(null)
    val viewStates: StateFlow<STATE?> = _viewStates.asStateFlow()

    private val _viewActions = MutableSharedFlow<ACTION>(replay = 0)
    val viewActions: Flow<ACTION> = _viewActions.asSharedFlow()

    private val _events = MutableSharedFlow<EVENT>(extraBufferCapacity = DEFAULT_EVENT_CAPACITY)

    /** Hilt РЕАЛЬНО удобная штука и пока нет желания делать костыли для ViewModel с кастомным DI решением */
    open fun onEvent(event: EVENT) {
        throw NotImplementedError("onEvent() должен быть переопределён")
    }

    val events: SharedFlow<EVENT> = _events.asSharedFlow()

    protected var viewState: STATE
        get() = _viewStates.value ?: throw UninitializedPropertyAccessException("\"viewState\" was not initialized")
        set(value) {
            _viewStates.value = value
        }

    protected fun sendAction(vararg actions: ACTION) {
        launch {
            _viewActions.emitAll(actions.asFlow())
        }
    }

    protected fun logError(throwable: Throwable) {
        Log.e("BaseViewModel", "Error occurred", throwable)
    }

    fun sendEvent(event: EVENT) {
        _events.tryEmit(event)
    }

    private fun subscribeToEvents() {
        launch {
            _events.collect { event -> onEvent(event) }
        }
    }

    companion object {
        private const val SUBSCRIPTION_STOP_TIMEOUT = 5_000L
        private const val DEFAULT_EVENT_CAPACITY = 64
    }
}