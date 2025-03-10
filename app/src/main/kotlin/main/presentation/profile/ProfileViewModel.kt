package main.presentation.profile

import com.example.fortniteapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import main.common.base.BaseViewModel
import main.common.utils.ResourcesHandler
import main.common.utils.ScopeProvider
import main.domain.profile.ProfileInteractor
import main.presentation.profile.interactions.ProfileAction
import main.presentation.profile.interactions.ProfileEvent
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val resourcesHandler: ResourcesHandler,
    scopeProvider: ScopeProvider
) : BaseViewModel<ProfileViewModel.State, ProfileAction, ProfileEvent>(scopeProvider) {

    init {
        viewState = State(
            type = State.Type.Init,
            noProfileTitle = resourcesHandler.getString(R.string.no_profile),
            submitButton = resourcesHandler.getString(R.string.submit)
        )
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UserIdChanged -> userIdChanged(event.text)
            ProfileEvent.SubmitButtonPressed -> onSubmitPressed()
        }
    }

    private fun userIdChanged(id: String?) {
        viewState = viewState.copy(userID = id.orEmpty())
    }

    private fun onSubmitPressed() {
        viewState = viewState.copy(type = State.Type.Loading)
        if (validateUserId(viewState.userID)) {
            loadUserData()
        } else {
            sendAction(ProfileAction.ShowErrorToast(resourcesHandler.getString(R.string.invalid_profile_id)))
            sendAction(ProfileAction.ShowInvalidUserId)
            viewState = viewState.copy(type = State.Type.Init)
        }
    }

    /**
     * ✅ От 3 до 16 символов
     * ✅ Разрешены буквы (A-Z, a-z), цифры (0-9), подчеркивания (_) и тире (-)
     * ✅ Не должно быть пробелов
     * ✅ Должно начинаться и заканчиваться буквой или цифрой
     * ✅ Нельзя два подряд _ или -
     */
    // TODO(вынести для переиспользования. на данном этапе нет необходимости)
    private fun validateUserId(id: String?): Boolean {
        if (id.isNullOrBlank()) return false  // Проверяем, что не пусто

        val regex = Regex("^[A-Za-z0-9](?!.*[-_]{2})[A-Za-z0-9_-]{1,14}[A-Za-z0-9]$")
        return regex.matches(id)
    }

    private fun loadUserData() {
        launch(onError = ::onError) {
            val profile = profileInteractor.loadData(viewState.userID)
            viewState = viewState.copy(type = State.Type.Init)
        }
    }

    private fun onError(e: Throwable) {
        sendAction(ProfileAction.ShowErrorToast(e.message ?: ""))
        viewState = viewState.copy(type = State.Type.Init)
    }

    data class State(
        val type: Type,
        val noProfileTitle: String,
        val userID: String = "",
        val submitButton: String
    ) {
        sealed interface Type {
            data object Init : Type
            data object Data : Type
            data object Loading : Type
        }
    }
}