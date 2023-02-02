package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getCharacterListFromDbUseCase: GetCharacterListFromDbUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    val listFromDb = getCharacterListFromDbUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun getDetailCharacter(character: Character, navigation: (Character) -> Unit) {
        getCharacterDetailUseCase.invoke(character, navigation)
    }
}