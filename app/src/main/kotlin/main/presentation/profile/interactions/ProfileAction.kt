package main.presentation.profile.interactions

sealed interface ProfileAction {
    data class ShowErrorToast(val message: String) : ProfileAction
    data object ShowInvalidUserId : ProfileAction
}