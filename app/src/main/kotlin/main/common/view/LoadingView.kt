package main.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.fortniteapp.databinding.ViewLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewLoadingBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = ViewLoadingBinding.inflate(inflater, this, true)
        binding.loadingView.visibility = View.GONE
    }

    fun show(show: Boolean = true) {
        binding.loadingView.visibility = if (show) View.VISIBLE else View.GONE
        bringToFront()
    }

    fun hide() {
        binding.loadingView.visibility = View.GONE
    }
}