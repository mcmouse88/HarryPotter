package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.AddToFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.CheckCharacterFromDbUseCase
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getCharacterFromDbUseCase: CheckCharacterFromDbUseCase
) : ViewModel() {


    fun addToFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavoriteUseCase.invoke(character)
        }
    }

    fun getCharacterFromDb(name: String): Boolean {
        return getCharacterFromDbUseCase.invoke(name)
    }

    fun deleteFromFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavoriteUseCase.invoke(character)
        }
    }
}