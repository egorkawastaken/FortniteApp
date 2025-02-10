package main.presentation.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fortniteapp.databinding.FrShopBinding

class ShopFragment: Fragment() {

    private lateinit var binding: FrShopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrShopBinding.inflate(inflater, container, false)
        return binding.root
    }
}