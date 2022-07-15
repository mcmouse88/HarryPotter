package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getCharacterListFromDbUseCase: GetCharacterListFromDbUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {
    val listFromDb = getCharacterListFromDbUseCase.invoke()

    fun getDetailCharacter(character: Character, navigation: (Character) -> Unit) {
        getCharacterDetailUseCase.invoke(character, navigation)
    }
}