package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.AddToFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.CheckCharacterFromDbUseCase
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getCharacterFromDbUseCase: CheckCharacterFromDbUseCase,
    @Assisted(ASSISTED_NAME) private val name: String
) : ViewModel() {

    val isFavorite: StateFlow<Boolean> = flow {
        emit(getCharacterFromDbUseCase.invoke(name))
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun addToFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavoriteUseCase.invoke(character)
        }
    }

    fun deleteFromFavorite(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavoriteUseCase.invoke(character)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(ASSISTED_NAME) name: String
        ): DetailViewModel
    }

    private companion object {
        private const val ASSISTED_NAME = "characterName"
    }
}