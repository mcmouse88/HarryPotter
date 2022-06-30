package com.mcmouse88.harrypotter.data.room.repository

import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.entity.Character

interface RoomRepository {

    val allCharacterFromDb: LiveData<List<DatabaseCharacterModel>>

    fun getCharacterFromDb(name: String): LiveData<DatabaseCharacterModel>

    suspend fun insertCharacter(character: DatabaseCharacterModel)

    suspend fun deleteCharacter(character: DatabaseCharacterModel)
}