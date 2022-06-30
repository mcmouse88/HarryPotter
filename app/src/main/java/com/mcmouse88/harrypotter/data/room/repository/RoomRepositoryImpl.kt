package com.mcmouse88.harrypotter.data.room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.data.room.database.CharacterDataBase
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.entity.Character

class RoomRepositoryImpl(
    application: Application
) : RoomRepository {

    private val roomDao = CharacterDataBase.getInstance(application).getRoomDao()

    override val allCharacterFromDb: LiveData<List<DatabaseCharacterModel>>
        get() = roomDao.getAllCharacterFromDb()

    override fun getCharacterFromDb(name: String): LiveData<DatabaseCharacterModel> =
        roomDao.getCharacterFromDb(name)


    override suspend fun insertCharacter(character: DatabaseCharacterModel) {
        roomDao.insertCharacter(character)
    }

    override suspend fun deleteCharacter(character: DatabaseCharacterModel) {
        roomDao.deleteCharacter(character)
    }

}