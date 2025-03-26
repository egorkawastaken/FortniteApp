package main.presentation.profile.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrEmptyProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import main.common.base.BaseFragment
import main.common.base.toast.ToastModel
import main.presentation.profile.empty.EmptyProfileViewModel.State
import main.presentation.profile.empty.interactions.EmptyProfileAction
import main.presentation.profile.empty.interactions.EmptyProfileEvent

@AndroidEntryPoint
class EmptyProfileFragment :
    BaseFragment<FrEmptyProfileBinding, EmptyProfileViewModel, State, EmptyProfileAction, EmptyProfileEvent>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FrEmptyProfileBinding = FrEmptyProfileBinding.inflate(layoutInflater)

    override val viewModel: EmptyProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            profileInputText.setOnTextChangedListener { text, _, _, _ ->
                sendEvent(EmptyProfileEvent.UserIdChanged(text.toString()))
            }
            submitButton.setOnClickListener {
                sendEvent(EmptyProfileEvent.SubmitButtonPressed)
            }
        }
    }

    override fun handleAction(action: EmptyProfileAction) {
        when (action) {
            is EmptyProfileAction.ShowErrorToast -> showErrorToast(action)
            EmptyProfileAction.ShowInvalidUserId -> showInvalidUserId()
            is EmptyProfileAction.OpenProfile -> openProfile(action.userId)
        }
    }

    override fun handleState(state: State) {
        when (state.type) {
            State.Type.Data -> handleDataState(state)
            State.Type.Loading -> handleLoadingState()
        }
    }

    private fun handleDataState(state: State) {
        with(binding) {
            loadingView.hide()
            noProfileText.text = state.noProfileTitle
            profileInputText.setText(state.userID)
            submitButton.text = state.submitButton
        }
    }

    private fun handleLoadingState() {
        binding.loadingView.show()
    }

    private fun showErrorToast(action: EmptyProfileAction.ShowErrorToast) {
        binding.toast.showToast(message = action.message, type = ToastModel.ToastType.ERROR)
    }

    private fun showInvalidUserId() {
        binding.profileInputText.showError()
    }

    private fun openProfile(userId: String) {
        val action = EmptyProfileFragmentDirections.emptyToProfile(userId)
        navigateWithAction(action)
    }
}