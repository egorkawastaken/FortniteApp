package main.common.base.toast

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.fortniteapp.R
import com.example.fortniteapp.databinding.ViewToastBinding
import java.util.LinkedList
import java.util.Queue

class CustomToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewToastBinding.inflate(LayoutInflater.from(context), this, true)
    private val toastQueue: Queue<ToastModel> = LinkedList()
    private var isShowing = false

    init {
        visibility = View.GONE

        val defaultMargin = context.resources.getDimensionPixelSize(R.dimen.default_margin)

        elevation = defaultMargin.dpToPx()
        setPadding(defaultMargin, defaultMargin, defaultMargin, defaultMargin)
    }

    fun showToast(
        message: String,
        type: ToastModel.ToastType = ToastModel.ToastType.NOTIFICATION,
        duration: Long = DEFAULT_DURATION
    ) {
        toastQueue.add(ToastModel(message, type, duration))
        if (!isShowing) {
            processQueue()
        }
    }

    private fun processQueue() {
        if (toastQueue.isEmpty() || isShowing) return
        isShowing = true

        val nextToast = toastQueue.poll() ?: return
        displayToast(nextToast)
    }

    private fun displayToast(toastModel: ToastModel) {
        binding.textView.text = toastModel.message

        val background = ContextCompat.getDrawable(context, R.drawable.bg_toast)?.mutate()

        val color = when (toastModel.type) {
            ToastModel.ToastType.SUCCESS -> ContextCompat.getColor(context, R.color.success)
            ToastModel.ToastType.NOTIFICATION -> ContextCompat.getColor(context, R.color.notification)
            ToastModel.ToastType.ERROR -> ContextCompat.getColor(context, R.color.error)
        }

        background?.let {
            DrawableCompat.setTint(it, color)
            binding.container.background = it
        }

        animateIn()

        postDelayed({
                        animateOut {
                            isShowing = false
                            processQueue()
                        }
                    }, toastModel.duration)
    }

    private fun animateIn() {
        if (visibility == View.VISIBLE) return
        visibility = View.VISIBLE
        alpha = 0f
        animate().alpha(1f).setDuration(ANIMATION_DURATION).start()
    }

    private fun animateOut(onEnd: () -> Unit) {
        animate().alpha(0f).setDuration(ANIMATION_DURATION).withEndAction {
            visibility = View.GONE
            onEnd()
        }.start()
    }

    private fun Int.dpToPx(): Float {
        return this * Resources.getSystem().displayMetrics.density
    }

    companion object {
        private const val DEFAULT_DURATION = 3000L
        private const val ANIMATION_DURATION = 400L
    }
}
