package main.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<VBinding : ViewBinding,
    VModel : BaseViewModel<STATE, ACTION, EVENT>, STATE : Any, ACTION : Any, EVENT : Any> : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectState()
        collectActions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Очистка ссылки на binding, чтобы избежать утечек памяти
        _binding = null
    }

    /** Обработка состояния */
    protected abstract fun handleState(state: STATE)

    /** Обработка ACTION */
    protected abstract fun handleAction(action: ACTION)

    /** Подписка на STATE */
    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewStates.collect { state ->
                    state?.let { handleState(it) }
                }
            }
        }
    }

    private fun collectActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewActions.collect { action ->
                    handleAction(action)
                }
            }
        }
    }

    /** Отправка EVENT */
    protected fun sendEvent(event: EVENT) {
        viewModel.sendEvent(event)
    }

    protected fun navigateWithAction(action: NavDirections) {
        findNavController().navigate(action)
    }
}