package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getCharacterListFromDbUseCase: GetCharacterListFromDbUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(getCharacterListFromDbUseCase, getCharacterDetailUseCase) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}