package main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fortniteapp.R
import com.example.fortniteapp.databinding.VStatsCardBinding
import com.google.android.material.card.MaterialCardView

class StatsCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding = VStatsCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        radius = resources.getDimension(R.dimen.default_corner_radius)
        elevation = resources.getDimension(R.dimen.default_card_elevation)

        context.theme.obtainStyledAttributes(attrs, R.styleable.StatsCardView, defStyleAttr, 0).apply {
            try {
                val titleText = getString(R.styleable.StatsCardView_titleText) ?: ""
                val titleTextColor = getColor(
                    R.styleable.StatsCardView_titleTextColor,
                    ContextCompat.getColor(context, R.color.black)
                )
                val titleBackgroundColor = getColor(
                    R.styleable.StatsCardView_titleBackgroundColor,
                    ContextCompat.getColor(context, R.color.white)
                )

                setTitle(titleText)
                setTitleTextColor(titleTextColor)
                setTitleBackgroundColor(titleBackgroundColor)
            } finally {
                recycle()
            }
        }
    }

    fun setTitle(title: String) {
        binding.statsTitle.text = title
    }

    fun setTitleTextColor(@ColorInt color: Int) {
        binding.statsTitle.setTextColor(color)
    }

    fun setTitleBackgroundColor(@ColorInt color: Int) {
        binding.statsTitle.setBackgroundColor(color)
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        binding.statsRecycler.adapter = adapter
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        binding.statsRecycler.layoutManager = layoutManager
    }
}
