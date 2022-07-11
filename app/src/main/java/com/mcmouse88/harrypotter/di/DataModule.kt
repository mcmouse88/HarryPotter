package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import org.koin.dsl.module

val dataModule = module {
    single<UseCaseRepository> { UseCaseRepositoryImpl(application = get()) }

    single { ApiRepository() }
}