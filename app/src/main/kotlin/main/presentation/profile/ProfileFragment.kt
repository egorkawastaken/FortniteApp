package main.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import main.common.base.BaseFragment
import main.common.base.toast.ToastModel
import main.presentation.profile.interactions.ProfileAction
import main.presentation.profile.interactions.ProfileEvent

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FrProfileBinding, ProfileViewModel, ProfileViewModel.State, ProfileAction, ProfileEvent>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FrProfileBinding = FrProfileBinding.inflate(layoutInflater)

    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            profileInputText.setOnTextChangedListener { text, _, _, _ ->
                viewModel.onEvent(ProfileEvent.UserIdChanged(text.toString()))
            }
            submitButton.setOnClickListener {
                viewModel.onEvent(ProfileEvent.SubmitButtonPressed)
            }
        }
    }

    override fun handleAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.ShowErrorToast -> showErrorToast(action)
            ProfileAction.ShowInvalidUserId -> showInvalidUserId()
        }
    }

    override fun handleState(state: ProfileViewModel.State) {
        when (state.type) {
            ProfileViewModel.State.Type.Init -> handleInitialState(state)
            ProfileViewModel.State.Type.Loading -> handleLoadingState()
            ProfileViewModel.State.Type.Data -> TODO()
        }
    }

    private fun handleInitialState(state: ProfileViewModel.State) {
        with(binding) {
            loadingView.hide()
            initStub.isVisible = true
            noProfileText.text = state.noProfileTitle
            profileInputText.setText(state.userID)
            submitButton.text = state.submitButton
        }
    }

    private fun handleLoadingState() {
        binding.loadingView.show()
    }

    private fun showErrorToast(action: ProfileAction.ShowErrorToast) {
        binding.toast.showToast(message = action.message, type = ToastModel.ToastType.ERROR)
    }

    private fun showInvalidUserId() {
        binding.profileInputText.showError()
    }
}