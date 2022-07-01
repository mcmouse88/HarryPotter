package com.mcmouse88.harrypotter.domain.usecaserepository

import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.domain.entity.Character

interface UseCaseRepository {

    suspend fun addToFavoriteUseCase(character: Character)

    suspend fun deleteFromFavoriteUseCase(character: Character)

    fun getCharacterDetail(character: Character, navigation: (Character) -> Unit)

    fun checkCharacterFromDbUseCase(name: String): Boolean

    fun getCharacterListFromDbUseCase(): LiveData<List<Character>>
}