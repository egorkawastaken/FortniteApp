package main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fortniteapp.databinding.FrProfileBinding

class ProfileFragment: Fragment() {

    private lateinit var binding: FrProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}