package com.mcmouse88.harrypotter.domain.usecaserepository

import com.mcmouse88.harrypotter.domain.entity.Character

interface UseCaseRepository {

    suspend fun addToFavoriteUseCase(character: Character)

    suspend fun deleteFromFavoriteUseCase(character: Character)

    fun getCharacterDetail(): Character

    fun getCharacterFromDbUseCase(): Character

    fun getCharacterListFromDbUseCase(): List<Character>
}