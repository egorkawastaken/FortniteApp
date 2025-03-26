package main.common.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourcesHandler {

    override fun getString(@StringRes stringId: Int, vararg args: Any?): String {
        return context.getString(stringId, *args)
    }
}
