package com.mcmouse88.harrypotter.domain.usecaserepository

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.mcmouse88.harrypotter.domain.entity.Character

interface UseCaseRepository {

    suspend fun addToFavoriteUseCase(character: Character)

    suspend fun deleteFromFavoriteUseCase(character: Character)

    fun getCharacterDetail(navController: NavController, character: Character)

    fun getCharacterFromDbUseCase(name: String): LiveData<Character>

    fun getCharacterListFromDbUseCase(): LiveData<List<Character>>
}