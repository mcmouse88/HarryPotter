package com.mcmouse88.harrypotter.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any?): String {
        return context.getString(stringRes, *formatArgs)
    }
}