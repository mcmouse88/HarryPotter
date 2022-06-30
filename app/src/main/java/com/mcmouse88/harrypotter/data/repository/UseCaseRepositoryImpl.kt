package com.mcmouse88.harrypotter.data.repository

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class UseCaseRepositoryImpl : UseCaseRepository {

    override suspend fun addToFavoriteUseCase(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavoriteUseCase(characterItem: Character) {
        TODO("Not yet implemented")
    }

    override fun getCharacterDetail(): Character {
        TODO("Not yet implemented")
    }

    override fun getCharacterFromDbUseCase(): Character {
        TODO("Not yet implemented")
    }

    override fun getCharacterListFromDbUseCase(): List<Character> {
        TODO("Not yet implemented")
    }

}