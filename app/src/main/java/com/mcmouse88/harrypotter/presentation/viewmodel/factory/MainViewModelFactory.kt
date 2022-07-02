package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor (
    private val apiRepository: ApiRepository,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiRepository, getCharacterDetailUseCase) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class $modelClass")
        }
    }
}