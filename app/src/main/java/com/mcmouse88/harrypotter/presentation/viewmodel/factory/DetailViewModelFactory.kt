package com.mcmouse88.harrypotter.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.domain.usecase.AddToFavoriteUseCase
import com.mcmouse88.harrypotter.domain.usecase.CheckCharacterFromDbUseCase
import com.mcmouse88.harrypotter.domain.usecase.DeleteFromFavoriteUseCase
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor (
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
            private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
            private val getCharacterFromDbUseCase: CheckCharacterFromDbUseCase
) : ViewModelProvider.Factory {

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