package com.mcmouse88.harrypotter.data.room.repository

import com.mcmouse88.harrypotter.data.room.dao.CharacterDao
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : RoomRepository {

    override val allCharacterFromDb: Flow<List<DatabaseCharacterModel>>
        get() = characterDao.getAllCharacterFromDb()

    override suspend fun checkCharacterFromDb(name: String): Boolean =
        characterDao.checkCharacterOnDb(name)


    override suspend fun insertCharacter(character: DatabaseCharacterModel) {
        characterDao.insertCharacter(character)
    }

    override suspend fun deleteCharacter(character: DatabaseCharacterModel) {
        characterDao.deleteCharacter(character)
    }

}