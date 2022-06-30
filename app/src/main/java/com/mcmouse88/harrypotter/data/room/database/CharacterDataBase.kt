package com.mcmouse88.harrypotter.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcmouse88.harrypotter.data.room.dao.RoomDao
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.utils.Constants.DB_NAME

@Database(entities = [DatabaseCharacterModel::class] , version = 2)
abstract class CharacterDataBase : RoomDatabase()  {
    abstract fun getRoomDao(): RoomDao

    companion object {
        @Volatile
        private var instance: CharacterDataBase? = null

        @Synchronized
        fun getInstance(context: Context): CharacterDataBase {
            return if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CharacterDataBase::class.java,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                instance as CharacterDataBase
            } else {
                instance as CharacterDataBase
            }
        }
    }
}