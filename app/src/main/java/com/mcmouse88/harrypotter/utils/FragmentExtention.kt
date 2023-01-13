package com.mcmouse88.harrypotter.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.observeFlow(owner: LifecycleOwner, block: (T) -> Unit) {
    owner.lifecycleScope.launch {
        flowWithLifecycle(owner.lifecycle, Lifecycle.State.STARTED)
            .collect {
                block.invoke(it)
            }
    }
}