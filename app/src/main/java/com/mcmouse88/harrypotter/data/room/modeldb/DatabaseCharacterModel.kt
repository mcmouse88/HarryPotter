package com.mcmouse88.harrypotter.data.room.modeldb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mcmouse88.harrypotter.domain.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DatabaseCharacterModel(
    @PrimaryKey
    val name: String,
    val alive: Boolean,
    @ColumnInfo(name = "date_birthday")
    val dateOfBirth: String,
    val gender: String,
    val house: String,
    val image: String,
    val species: String,
    val ancestry: String
)