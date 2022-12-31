package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.ReadAllCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    private val readAllCharacterUseCase: ReadAllCharacterUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _listCharacter = MutableStateFlow<List<Character>?>(null)
    val listCharacter: StateFlow<List<Character>?>
        get() = _listCharacter

    private val _screenState = MutableStateFlow<StateScreen>(StateScreen.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        getListCharacter()
    }

    private fun getListCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            readAllCharacterUseCase.invoke().let {
                _listCharacter.tryEmit(it)
            }
            _screenState.tryEmit(StateScreen.Content)
        }
    }

    fun getDetailCharacter(
        character: Character,
        navigation: (Character) -> Unit
    ) {
        getCharacterDetailUseCase.invoke(character, navigation)
    }

    sealed interface StateScreen {
        object Content : StateScreen
        object Loading : StateScreen
    }
}