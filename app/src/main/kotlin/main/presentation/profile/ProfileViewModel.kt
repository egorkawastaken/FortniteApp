package main.presentation.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import main.common.base.BaseViewModel
import main.presentation.profile.interactions.ProfileAction
import main.presentation.profile.interactions.ProfileEvent

@HiltViewModel
class ProfileViewModel : BaseViewModel<ProfileViewModel.State, ProfileAction, ProfileEvent>(
    initialState = State(
        buttonText = "load data",
        type = State.Type.Loading)
) {

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            else -> Unit
        }
    }

    data class State(
        val buttonText: String,
        val type: Type
    ) {
        sealed interface Type {
            data object Data : Type
            data object Loading : Type
        }
    }
}