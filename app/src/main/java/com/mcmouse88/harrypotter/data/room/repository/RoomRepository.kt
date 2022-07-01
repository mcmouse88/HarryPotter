package com.mcmouse88.harrypotter.data.room.repository

import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel

interface RoomRepository {

    val allCharacterFromDb: LiveData<List<DatabaseCharacterModel>>

    fun checkCharacterFromDb(name: String): Boolean

    suspend fun insertCharacter(character: DatabaseCharacterModel)

    suspend fun deleteCharacter(character: DatabaseCharacterModel)
}