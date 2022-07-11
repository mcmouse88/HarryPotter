package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.domain.usecase.*
import org.koin.dsl.module

val domainModel = module {
    factory { GetCharacterDetailUseCase(repository = get()) }

    factory { GetCharacterListFromDbUseCase(repository = get()) }

    factory { AddToFavoriteUseCase(repository = get()) }

    factory { DeleteFromFavoriteUseCase(repository = get()) }

    factory { CheckCharacterFromDbUseCase(repository = get()) }
}