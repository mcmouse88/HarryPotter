package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class CheckCharacterFromDbUseCase(private val repository: UseCaseRepository) {

    operator fun invoke(name: String): Boolean {
        return repository.checkCharacterFromDbUseCase(name)
    }
}