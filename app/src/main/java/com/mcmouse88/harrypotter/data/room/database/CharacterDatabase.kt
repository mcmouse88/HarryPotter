package com.mcmouse88.harrypotter.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mcmouse88.harrypotter.data.room.dao.CharacterDao
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel

@Database(entities = [DatabaseCharacterModel::class] , version = 2)
abstract class CharacterDatabase : RoomDatabase()  {
    abstract fun getRoomDao(): CharacterDao
}