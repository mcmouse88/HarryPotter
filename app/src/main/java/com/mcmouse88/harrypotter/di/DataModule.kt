package com.mcmouse88.harrypotter.di

import android.app.Application
import android.content.Context
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.data.room.mapper.DataBaseModelMapper
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.data.room.repository.RoomRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    /**
     * Для того, чтобы передать контекст в Hilt в конструкторе достаточно указать аннотацию
     * [ApplicationContext]
     */
    @Provides
    @Singleton
    fun provideUseCaseRepository(
        roomRepository: RoomRepository,
        mapper: DataBaseModelMapper
    ): UseCaseRepository {
        return UseCaseRepositoryImpl(roomRepository, mapper)
    }

    @Provides
    @Singleton
    fun provideRoomRepository(@ApplicationContext context: Context): RoomRepository {
        return RoomRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideDataBaseModelMapper(): DataBaseModelMapper {
        return DataBaseModelMapper()
    }

    @Provides
    @Singleton
    fun provideApiRepository(): ApiRepository {
        return ApiRepository()
    }
}