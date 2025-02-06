package main.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fortniteapp.databinding.FrProfileBinding
import main.common.base.BaseFragment

class ProfileFragment : BaseFragment<FrProfileBinding, ProfileViewModel>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FrProfileBinding = FrProfileBinding.inflate(layoutInflater)

    override val viewModel: ProfileViewModel by viewModels()
}