package main.presentation.profile.empty.interactions

sealed interface EmptyProfileAction {
    data class ShowErrorToast(val message: String) : EmptyProfileAction
    data object ShowInvalidUserId : EmptyProfileAction
    data class OpenProfile(val userId: String) : EmptyProfileAction
}