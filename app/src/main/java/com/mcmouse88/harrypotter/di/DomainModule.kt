package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.domain.usecase.*
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideAddToFavoriteUseCase(impl: UseCaseRepository): AddToFavoriteUseCase {
        return AddToFavoriteUseCase(impl)
    }

    @Provides
    fun provideCheckCharacterFromDbUseCase(impl: UseCaseRepository): CheckCharacterFromDbUseCase {
        return CheckCharacterFromDbUseCase(impl)
    }

    @Provides
    fun provideDeleteFromFavoriteUseCase(impl: UseCaseRepository): DeleteFromFavoriteUseCase {
        return DeleteFromFavoriteUseCase(impl)
    }

    @Provides
    fun provideGetCharacterDetailUseCase(impl: UseCaseRepository): GetCharacterDetailUseCase {
        return GetCharacterDetailUseCase(impl)
    }

    @Provides
    fun provideGetCharacterListFromDbUseCase(impl: UseCaseRepository): GetCharacterListFromDbUseCase {
        return GetCharacterListFromDbUseCase(impl)
    }


}