package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.data.room.mapper.DataBaseModelMapper
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.data.room.repository.RoomRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import org.koin.dsl.module

val dataModule = module {
    single<UseCaseRepository> {
        UseCaseRepositoryImpl(
            roomRepository = get(),
            mapper = get()
        )
    }

    single<RoomRepository> {
        RoomRepositoryImpl(application = get())
    }

    factory {
        DataBaseModelMapper()
    }

    single { ApiRepository() }
}