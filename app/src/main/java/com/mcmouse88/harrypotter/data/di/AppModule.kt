package com.mcmouse88.harrypotter.data.di

import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun bindsUseCaseRepository(impl: UseCaseRepositoryImpl): UseCaseRepository

    @Binds
    fun bindsRoomRepository(impl: RoomRepository): RoomRepository
}