package main.common.base.toast

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import com.example.fortniteapp.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackUtils @Inject constructor(
    @ApplicationContext private val appContext: Context
) {

    fun showSnack(message: String, type: SnackType = SnackType.ERROR, anchorView: View) {
        val snackbar = Snackbar.make(anchorView, message, Snackbar.LENGTH_LONG)

        val snackbarView = snackbar.view
        val backgroundColor = when (type) {
            SnackType.SUCCESS -> ContextCompat.getColor(appContext, R.color.success)
            SnackType.NOTIFICATION -> ContextCompat.getColor(appContext, R.color.notification)
            SnackType.ERROR -> ContextCompat.getColor(appContext, R.color.error)
        }
        snackbarView.backgroundTintList = ColorStateList.valueOf(backgroundColor)
        snackbar.setTextColor(ContextCompat.getColor(appContext, android.R.color.white))
        snackbar.setTextMaxLines(2)
        snackbarView.background = ContextCompat.getDrawable(appContext, R.drawable.bg_snack)

        snackbar.show()
    }
}