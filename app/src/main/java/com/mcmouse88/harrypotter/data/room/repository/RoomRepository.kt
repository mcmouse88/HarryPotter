package com.mcmouse88.harrypotter.data.room.repository

import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    val allCharacterFromDb: Flow<List<DatabaseCharacterModel>>

    suspend fun checkCharacterFromDb(name: String): Boolean

    suspend fun insertCharacter(character: DatabaseCharacterModel)

    suspend fun deleteCharacter(character: DatabaseCharacterModel)
}