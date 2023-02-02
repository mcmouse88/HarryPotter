package com.mcmouse88.harrypotter.domain.usecaserepository

import com.mcmouse88.harrypotter.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface UseCaseRepository {

    suspend fun addToFavoriteUseCase(character: Character)

    suspend fun deleteFromFavoriteUseCase(character: Character)

    fun getCharacterDetail(character: Character, navigation: (Character) -> Unit)

    suspend fun checkCharacterFromDbUseCase(name: String): Boolean

    fun getCharacterListFromDbUseCase(): Flow<List<Character>>

    suspend fun readAllCharacter(): List<Character>?
}