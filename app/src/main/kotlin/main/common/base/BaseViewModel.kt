package main.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import main.common.utils.BaseScopeContainer
import main.common.utils.DefaultContextProvider
import main.common.utils.ScopeProvider

abstract class BaseViewModel<STATE : Any, ACTION : Any>(
    private val scopeProvider: ScopeProvider = ScopeProvider(DefaultContextProvider()),
    initialState: STATE
) : ViewModel(), BaseScopeContainer {

    final override val mainScope: CoroutineScope = scopeProvider.mainScope
    final override val ioScope: CoroutineScope = scopeProvider.ioScope

    private val _viewStates = MutableStateFlow(initialState)
    val viewStates: StateFlow<STATE> = _viewStates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(SUBSCRIPTION_STOP_TIMEOUT), initialState)

    private val _viewActions = MutableSharedFlow<ACTION>(replay = 0)
    val viewActions: Flow<ACTION> = _viewActions.asSharedFlow()

    protected var viewState: STATE
        get() = _viewStates.value
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

    companion object {
        private const val SUBSCRIPTION_STOP_TIMEOUT = 5_000L
    }
}