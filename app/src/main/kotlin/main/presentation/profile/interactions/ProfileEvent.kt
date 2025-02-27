package main.presentation.profile.interactions

sealed interface ProfileEvent {
    data object LoadData : ProfileEvent
    data class UserIdChanged(val text: String?) : ProfileEvent
}