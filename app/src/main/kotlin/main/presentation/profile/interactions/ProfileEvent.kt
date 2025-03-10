package main.presentation.profile.interactions

sealed interface ProfileEvent {
    data object SubmitButtonPressed : ProfileEvent
    data class UserIdChanged(val text: String?) : ProfileEvent
}