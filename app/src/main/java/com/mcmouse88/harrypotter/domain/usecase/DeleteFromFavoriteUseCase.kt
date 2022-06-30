package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class DeleteFromFavoriteUseCase(private val repository: UseCaseRepository) {

    suspend operator fun invoke(character: Character) {
        repository.deleteFromFavoriteUseCase(character)
    }
}