package com.mcmouse88.harrypotter.di

import android.content.Context
import androidx.room.Room
import com.mcmouse88.harrypotter.data.room.dao.CharacterDao
import com.mcmouse88.harrypotter.data.room.database.CharacterDatabase
import com.mcmouse88.harrypotter.domain.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class DatabaseModule {

    @[Provides Singleton]
    fun provideCharacterDatabase(
        @ApplicationContext context: Context
    ): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @[Provides Singleton]
    fun provideRoomDao(
        database: CharacterDatabase
    ): CharacterDao {
        return database.getRoomDao()
    }
}