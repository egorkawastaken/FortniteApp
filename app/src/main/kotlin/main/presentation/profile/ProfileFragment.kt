package main.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import main.common.base.BaseFragment
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
        binding.editText.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(ProfileEvent.UserIdChanged(text.toString()))
        }
        binding.button.setOnClickListener {
            viewModel.onEvent(ProfileEvent.LoadData)
        }
    }

    override fun handleAction(action: ProfileAction) {

    }

    override fun handleState(state: ProfileViewModel.State) {
        with(binding) {
            if (editText.text.toString() != state.userId && !state.userId.isNullOrEmpty()) {
                editText.setText(state.userId)
                editText.setSelection(state.userId.length)
            }
            sampleJson.text = state.text
        }
    }
}