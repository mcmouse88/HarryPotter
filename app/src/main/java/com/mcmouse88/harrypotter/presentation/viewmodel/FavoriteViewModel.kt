package com.mcmouse88.harrypotter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase
import com.mcmouse88.harrypotter.presentation.rvadapter.FavoriteItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getCharacterListFromDbUseCase: GetCharacterListFromDbUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModel() {

    val listFromDb = getCharacterListFromDbUseCase.invoke()
        .map { list ->
            delay(400L)
            if (list.isNotEmpty()) {
                val favorites = mutableListOf<FavoriteItems.FillItems>()
                list.forEach { character ->
                        favorites.add(FavoriteItems.FillItems(character))
                }
                ViewState.Content(favorites.toList())
            } else ViewState.NoContent(listOf(FavoriteItems.EmptyItem))
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ViewState.Loading)

    fun getDetailCharacter(character: Character, navigation: (Character) -> Unit) {
        getCharacterDetailUseCase.invoke(character, navigation)
    }

    fun deleteCharacter(item: Character) {
        viewModelScope.launch {
            deleteFromFavoriteUseCase.invoke(item)
        }
    }

    sealed interface ViewState {
        class Content(val characters: List<FavoriteItems>) : ViewState
        class NoContent(val placeHolder: List<FavoriteItems>) : ViewState
        object Loading : ViewState
    }
}