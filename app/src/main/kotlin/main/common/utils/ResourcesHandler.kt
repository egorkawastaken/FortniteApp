package main.common.utils

import androidx.annotation.StringRes

interface ResourcesHandler {
    fun getString(@StringRes stringId: Int, vararg args: Any?): String
}
