package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.utils.AppExceptions
import com.mcmouse88.harrypotter.utils.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _commands = MutableSharedFlow<Commands>(
        replay = 0,
        extraBufferCapacity = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val commands = _commands.asSharedFlow()

    private val _screenState = MutableStateFlow<StateScreen>(StateScreen.Loading)
    val screenState = _screenState.asStateFlow()

    fun CoroutineScope.safeLaunch(dispatcher: CoroutineDispatcher = Dispatchers.IO, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatcher) {
            try {
                block.invoke(this)
                _screenState.tryEmit(StateScreen.Content)
            } catch (e: AppExceptions) {
                _screenState.tryEmit(StateScreen.Empty)
                when (e) {
                    is AppExceptions.BackendException -> {
                        _commands.tryEmit(emitCommand(R.string.connection_error))
                    }
                    is AppExceptions.ConnectionException -> {
                        _commands.tryEmit(emitCommand(R.string.network_error))
                    }
                    is AppExceptions.EmptyBodyException -> {
                        _commands.tryEmit(emitCommand(R.string.load_error))
                    }
                    is AppExceptions.ParseResponseException -> {
                        _commands.tryEmit(emitCommand(R.string.parse_error))
                    }
                    is AppExceptions.UnknownException -> {
                        _commands.tryEmit(emitCommand(R.string.unknown_error))
                    }
                }
            }
        }
    }

    private fun emitCommand(@StringRes message: Int): Commands {
        return Commands.ShowErrorMessage(
            resourceManager.getString(message)
        )
    }

    sealed interface Commands {
        class ShowErrorMessage(val message: String) : Commands
    }

    sealed interface StateScreen {
        object Content : StateScreen
        object Empty : StateScreen
        object Loading : StateScreen
    }
}