package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.data.room.repository.RoomRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @[Binds Singleton]
    fun bindUseCaseRepository(impl: UseCaseRepositoryImpl): UseCaseRepository

    @[Binds Singleton]
    fun provideRoomRepository(impl: RoomRepositoryImpl): RoomRepository
}