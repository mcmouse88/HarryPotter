package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.domain.usecase.*
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor (
    private val apiRepository: ApiRepository,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getCharacterListFromDbUseCase: GetCharacterListFromDbUseCase,
    private val getCharacterFromDbUseCase: CheckCharacterFromDbUseCase
) : ViewModelProvider.Factory {


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