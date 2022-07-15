package com.mcmouse88.harrypotter.data.room.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.data.room.database.CharacterDataBase
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel

class RoomRepositoryImpl(
    context: Context
) : RoomRepository {

    private val roomDao = CharacterDataBase.getInstance(context).getRoomDao()

    override val allCharacterFromDb: LiveData<List<DatabaseCharacterModel>>
        get() = roomDao.getAllCharacterFromDb()

    override fun checkCharacterFromDb(name: String): Boolean =
        roomDao.checkCharacterOnDb(name)


    override suspend fun insertCharacter(character: DatabaseCharacterModel) {
        roomDao.insertCharacter(character)
    }

    override suspend fun deleteCharacter(character: DatabaseCharacterModel) {
        roomDao.deleteCharacter(character)
    }

}