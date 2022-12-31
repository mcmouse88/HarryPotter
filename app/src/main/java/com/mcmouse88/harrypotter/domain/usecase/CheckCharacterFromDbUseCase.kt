package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import javax.inject.Inject

class CheckCharacterFromDbUseCase @Inject constructor(private val repository: UseCaseRepository) {

    suspend operator fun invoke(name: String): Boolean {
        return repository.checkCharacterFromDbUseCase(name)
    }
}