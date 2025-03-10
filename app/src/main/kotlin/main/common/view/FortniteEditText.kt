package main.common.view

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.fortniteapp.R
import com.example.fortniteapp.databinding.ViewEditTextBinding

class FortniteEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewEditTextBinding = ViewEditTextBinding.inflate(LayoutInflater.from(context), this)
    private var onTextChangedListener: ((text: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null

    init {
        minimumHeight = resources.getDimensionPixelSize(R.dimen.edit_text_height)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.edit_text_height))
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChangedListener?.invoke(s, start, before, count)
                clearError() // Автоматически убираем ошибку при исправлении
            }
        })
    }

    fun setOnTextChangedListener(listener: ((text: CharSequence?, start: Int, before: Int, count: Int) -> Unit)?) {
        onTextChangedListener = listener
    }

    /** Устанавливает текст */
    fun setText(text: String) {
        with(binding.editText) {
            setText(text)
            setSelection(text.length)
        }
    }

    /** Получает введенный текст */
    fun getText(): String {
        return binding.editText.text.toString()
    }

    /** Подсвечивает поле красным при ошибке */
    fun showError() {
        binding.textInputLayout.setBoxStrokeColorStateList(
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.error))
        )
    }

    /** Убирает ошибку и восстанавливает стандартный цвет */
    fun clearError() {
        binding.textInputLayout.setBoxStrokeColorStateList(
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue))
        )
    }
}
