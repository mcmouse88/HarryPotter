package com.mcmouse88.harrypotter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.utils.Constants.TABLE_NAME

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DatabaseCharacterModel)

    @Delete
    suspend fun deleteCharacter(character: DatabaseCharacterModel)

    @Query("select * from $TABLE_NAME")
    fun getAllCharacterFromDb(): LiveData<List<DatabaseCharacterModel>>

    @Query("select name from $TABLE_NAME where name=:name")
    fun getCharacterFromDb(name: String): LiveData<DatabaseCharacterModel>
}