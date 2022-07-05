package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecase.*
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel

class MainViewModelFactory(application: Application) : ViewModelProvider.Factory {
    private val repository = UseCaseRepositoryImpl(application)
    private val apiRepository = ApiRepository()
    private val getCharacterDetailUseCase = GetCharacterDetailUseCase(repository)
    private val getCharacterListFromDbUseCase = GetCharacterListFromDbUseCase(repository)
    private val addToFavoriteUseCase = AddToFavoriteUseCase(repository)
    private val deleteFromFavoriteUseCase = DeleteFromFavoriteUseCase(repository)
    private val getCharacterFromDbUseCase = CheckCharacterFromDbUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(apiRepository, getCharacterDetailUseCase) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                addToFavoriteUseCase,
                deleteFromFavoriteUseCase,
                getCharacterFromDbUseCase
            ) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(getCharacterListFromDbUseCase, getCharacterDetailUseCase) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}