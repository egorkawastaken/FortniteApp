package main.presentation.profile.profile.interactions

sealed interface ProfileAction {
    data class ShowToast(val userId: String?): ProfileAction
}