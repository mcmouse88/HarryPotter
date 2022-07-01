package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel

class MainViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val repository = UseCaseRepositoryImpl(application)
    private val apiRepository = ApiRepository()
    private val getCharacterDetailUseCase = GetCharacterDetailUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiRepository, getCharacterDetailUseCase) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class $modelClass")
        }
    }
}