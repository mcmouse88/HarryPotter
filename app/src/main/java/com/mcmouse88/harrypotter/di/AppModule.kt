package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            apiRepository = get(),
            getCharacterDetailUseCase = get(),
        )
    }

    viewModel {
        DetailViewModel(
            addToFavoriteUseCase = get(),
            deleteFromFavoriteUseCase = get(),
            getCharacterFromDbUseCase = get()
        )
    }

    viewModel {
        FavoriteViewModel(
            getCharacterDetailUseCase = get(),
            getCharacterListFromDbUseCase = get()
        )
    }
}