package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecase.AddToFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.CheckCharacterFromDbUseCase
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel

class DetailViewModelFactory(application: Application) : ViewModelProvider.Factory {
    private val repository = UseCaseRepositoryImpl(application)
    private val addToFavoriteUseCase = AddToFavoriteUseCase(repository)
    private val deleteFromFavoriteUseCase = DeleteFromFavoriteUseCase(repository)
    private val getCharacterFromDbUseCase = CheckCharacterFromDbUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                addToFavoriteUseCase,
                deleteFromFavoriteUseCase,
                getCharacterFromDbUseCase
            ) as T
        } else throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}