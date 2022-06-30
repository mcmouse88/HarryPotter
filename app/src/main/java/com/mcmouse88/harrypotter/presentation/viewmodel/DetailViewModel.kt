package com.mcmouse88.harrypotter.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.AddToFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UseCaseRepositoryImpl(application)
    private val addToFavoriteUseCase = AddToFavoriteUseCase(repository)
    private val deleteFromFavoriteUseCase = DeleteFromFavoriteUseCase(repository)

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
}