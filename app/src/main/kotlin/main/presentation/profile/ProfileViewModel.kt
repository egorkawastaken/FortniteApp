package main.presentation.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import main.common.base.BaseViewModel
import main.common.utils.ScopeProvider
import main.domain.profile.ProfileInteractor
import main.presentation.profile.interactions.ProfileAction
import main.presentation.profile.interactions.ProfileEvent
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    scopeProvider: ScopeProvider
) : BaseViewModel<ProfileViewModel.State, ProfileAction, ProfileEvent>(scopeProvider) {

    init {
        viewState = State(
            buttonText = "load data",
            type = State.Type.Data
        )
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UserIdChanged -> userIdChanged(event.text)
            ProfileEvent.LoadData -> loadData()
        }
    }

    private fun userIdChanged(id: String?) {
        viewState = viewState.copy(userId = id)
    }

    private fun loadData() {
        launch {
            val text = profileInteractor.loadData(viewState.userId.orEmpty())
            viewState = viewState.copy(text = text.text)
        }
    }

    data class State(
        val userId: String? = null,
        val buttonText: String,
        val text: String? = null,
        val type: Type
    ) {
        sealed interface Type {
            data object Data : Type
            data object Loading : Type
        }
    }
}