package main.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding : ViewBinding, VModel: BaseViewModel> : Fragment() {

    private var _binding: VBinding? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VModel

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Очистка ссылки на binding, чтобы избежать утечек памяти
        _binding = null
    }
}