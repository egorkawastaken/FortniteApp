package main.presentation.profile.empty

import com.example.fortniteapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import main.common.base.BaseViewModel
import main.common.utils.ResourcesHandler
import main.common.utils.ScopeProvider
import main.presentation.profile.empty.interactions.EmptyProfileAction
import main.presentation.profile.empty.interactions.EmptyProfileEvent
import main.presentation.profile.model.AccountUiModel
import main.presentation.profile.model.BattlePassUiModel
import main.presentation.profile.model.StatsUiModel
import javax.inject.Inject

@HiltViewModel
class EmptyProfileViewModel @Inject constructor(
    private val resourcesHandler: ResourcesHandler,
    scopeProvider: ScopeProvider
) : BaseViewModel<EmptyProfileViewModel.State, EmptyProfileAction, EmptyProfileEvent>(scopeProvider) {

    init {
        viewState = State(
            type = State.Type.Data,
            noProfileTitle = resourcesHandler.getString(R.string.no_profile),
            submitButton = resourcesHandler.getString(R.string.submit)
        )
    }

    override fun onEvent(event: EmptyProfileEvent) {
        when (event) {
            is EmptyProfileEvent.UserIdChanged -> userIdChanged(event.text)
            EmptyProfileEvent.SubmitButtonPressed -> onSubmitPressed()
        }
    }

    private fun userIdChanged(id: String?) {
        viewState = viewState.copy(userID = id?.trim().orEmpty())
    }

    private fun onSubmitPressed() {
        viewState = viewState.copy(type = State.Type.Loading)
        if (validateUserId(viewState.userID)) {
           sendAction(EmptyProfileAction.OpenProfile(viewState.userID))
        } else {
            sendAction(EmptyProfileAction.ShowErrorToast(resourcesHandler.getString(R.string.invalid_profile_id)))
            sendAction(EmptyProfileAction.ShowInvalidUserId)
        }
        viewState = viewState.copy(type = State.Type.Data)
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

    data class State(
        val type: Type,
        val noProfileTitle: String,
        val userID: String = "",
        val submitButton: String,
        val account: AccountUiModel? = null,
        val battlePass: BattlePassUiModel? = null,
        val stats: StatsUiModel? = null
    ) {
        sealed interface Type {
            data object Data : Type
            data object Loading : Type
        }
    }
}