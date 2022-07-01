package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel

class FavoriteViewModelFactory(
    application: Application
) : ViewModelProvider.Factory {

    private val repository = UseCaseRepositoryImpl(application)
    private val getCharacterListFromDbUseCase = GetCharacterListFromDbUseCase(repository)
    private val getCharacterDetailUseCase = GetCharacterDetailUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(getCharacterListFromDbUseCase, getCharacterDetailUseCase) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}