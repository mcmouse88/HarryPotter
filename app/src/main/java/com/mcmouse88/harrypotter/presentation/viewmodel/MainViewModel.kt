package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.ReadAllCharacterUseCase
import com.mcmouse88.harrypotter.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAllCharacterUseCase: ReadAllCharacterUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    resourceManager: ResourceManager
) : BaseViewModel(resourceManager) {

    private val _listCharacter = MutableStateFlow<List<Character>?>(null)
    val listCharacter: StateFlow<List<Character>?>
        get() = _listCharacter

    init {
        getListCharacter()
    }

    private fun getListCharacter() {
        viewModelScope.safeLaunch {
            readAllCharacterUseCase.invoke().let {
                _listCharacter.tryEmit(it)
            }
        }
    }

    fun getDetailCharacter(
        character: Character,
        navigation: (Character) -> Unit
    ) {
        getCharacterDetailUseCase.invoke(character, navigation)
    }
}