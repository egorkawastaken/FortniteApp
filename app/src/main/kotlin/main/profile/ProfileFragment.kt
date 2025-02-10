package main.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrProfileBinding
import main.common.base.BaseFragment
import main.profile.interactions.ProfileAction
import main.profile.interactions.ProfileEvent

class ProfileFragment :
    BaseFragment<FrProfileBinding, ProfileViewModel, ProfileViewModel.State, ProfileAction, ProfileEvent>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FrProfileBinding = FrProfileBinding.inflate(layoutInflater)

    override val viewModel: ProfileViewModel by viewModels()

    override fun handleAction(action: ProfileAction) {

    }

    override fun handleState(state: ProfileViewModel.State) {

    }
}