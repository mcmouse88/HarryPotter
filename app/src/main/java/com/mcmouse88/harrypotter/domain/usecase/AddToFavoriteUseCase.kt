package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(private val repository: UseCaseRepository) {

    suspend operator fun invoke(character: Character) {
        repository.addToFavoriteUseCase(character)
    }
}