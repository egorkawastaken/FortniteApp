package main.presentation.profile.empty.interactions

sealed interface EmptyProfileEvent {
    data object SubmitButtonPressed : EmptyProfileEvent
    data class UserIdChanged(val text: String?) : EmptyProfileEvent
}