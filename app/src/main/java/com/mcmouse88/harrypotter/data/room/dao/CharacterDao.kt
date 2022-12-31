package com.mcmouse88.harrypotter.data.room.dao

import androidx.room.*
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.utils.Constants.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DatabaseCharacterModel)

    @Delete
    suspend fun deleteCharacter(character: DatabaseCharacterModel)

    @Query("select * from $TABLE_NAME")
    fun getAllCharacterFromDb(): Flow<List<DatabaseCharacterModel>>

    @Query("select exists(select * from $TABLE_NAME where name=:name)")
    suspend fun checkCharacterOnDb(name: String): Boolean
}