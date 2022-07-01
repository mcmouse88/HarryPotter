package com.mcmouse88.harrypotter.data.room.mapper

import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.domain.entity.Character

class DataBaseModelMapper {

    fun mapEntityToDbModel(character: Character) =
        DatabaseCharacterModel(
            alive = character.alive,
            dateOfBirth = character.dateOfBirth,
            gender = character.gender,
            house = character.house,
            image = character.image,
            name = character.name,
            species = character.species,
            ancestry = character.ancestry
        )

    private fun mapDbModelToEntity(dbModel: DatabaseCharacterModel) =
        Character(
            alive = dbModel.alive,
            dateOfBirth = dbModel.dateOfBirth,
            gender = dbModel.gender,
            house = dbModel.house,
            image = dbModel.image,
            name = dbModel.name,
            species = dbModel.species,
            ancestry = dbModel.ancestry
        )

    fun mapListDbModelToListEntities(list: List<DatabaseCharacterModel>) =
        list.asSequence().map { mapDbModelToEntity(it) }.toList()

}