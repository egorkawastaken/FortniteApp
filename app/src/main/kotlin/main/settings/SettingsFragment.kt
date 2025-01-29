package main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fortniteapp.databinding.FrSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FrSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
}