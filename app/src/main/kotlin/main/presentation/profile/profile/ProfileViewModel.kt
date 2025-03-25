package main.presentation.profile.profile

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import main.common.base.BaseViewModel
import main.common.extension.getOrThrow
import main.common.utils.ResourcesHandler
import main.common.utils.ScopeProvider
import main.domain.profile.ProfileInteractor
import main.presentation.profile.empty.EmptyProfileViewModel
import main.presentation.profile.mapper.StatsUiMapper
import main.presentation.profile.model.AccountUiModel
import main.presentation.profile.model.BattlePassUiModel
import main.presentation.profile.model.GameModeStatsUiModel
import main.presentation.profile.profile.interactions.ProfileAction
import main.presentation.profile.profile.interactions.ProfileEvent
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val resourcesHandler: ResourcesHandler,
    private val statsUiMapper: StatsUiMapper,
    private val interactor: ProfileInteractor,
    scopeProvider: ScopeProvider
) : BaseViewModel<ProfileViewModel.State, ProfileAction, ProfileEvent>(scopeProvider) {

    private val userID = savedStateHandle.getOrThrow<String>(USER_ID)

    init {
        viewState = State(
            type = State.Type.Loading,
            userID = userID
        )
        loadData()
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun loadData() {
        launch {
            val profile = interactor.loadData(userID)
            val stats = statsUiMapper.map(profile.stats)
            viewState = viewState.copy(
                type = State.Type.Data,
                stats = stats.all
            )
        }
    }

    data class State(
        val type: Type,
        val userID: String? = null,
        val account: AccountUiModel? = null,
        val battlePass: BattlePassUiModel? = null,
        val stats: List<GameModeStatsUiModel>? = null
    ) {
        sealed interface Type {
            data object Data : Type
            data object Loading : Type
        }
    }

    companion object {
        private const val USER_ID = "userId"
    }
}