package main.presentation.profile.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import main.common.base.BaseFragment
import main.presentation.profile.profile.adapter.cards.StatsCardsAdapter
import main.presentation.profile.profile.interactions.ProfileAction
import main.presentation.profile.profile.interactions.ProfileEvent

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FrProfileBinding, ProfileViewModel, ProfileViewModel.State, ProfileAction, ProfileEvent>() {

    private val statsAdapter by lazy { StatsCardsAdapter() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FrProfileBinding = FrProfileBinding.inflate(inflater, container, false)

    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stats.apply {
            adapter = statsAdapter
            setHasFixedSize(true)
        }
    }

    override fun handleAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.ShowToast -> showToast(action.userId)
        }
    }

    override fun handleState(state: ProfileViewModel.State) {
        when (state.type) {
            ProfileViewModel.State.Type.Data -> handleDataState(state)
            ProfileViewModel.State.Type.Loading -> handleLoadingState()
        }
    }

    private fun handleDataState(state: ProfileViewModel.State) {
        binding.loadingView.hide()
        state.stats?.let { stats ->
            statsAdapter.setItems(stats)
        }
    }

    private fun showToast(userID: String?) {
        binding.toast.showToast(userID ?: "")
    }

    private fun handleLoadingState() {
        binding.loadingView.show()
    }
}