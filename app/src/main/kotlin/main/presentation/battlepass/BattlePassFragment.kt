package main.presentation.battlepass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fortniteapp.databinding.FrBattlePassBinding
import com.example.fortniteapp.databinding.FrShopBinding

class BattlePassFragment: Fragment() {

    private lateinit var binding: FrBattlePassBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrBattlePassBinding.inflate(inflater, container, false)
        return binding.root
    }
}