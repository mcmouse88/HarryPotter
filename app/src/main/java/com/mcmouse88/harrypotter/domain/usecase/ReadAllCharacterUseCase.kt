package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import javax.inject.Inject
import com.mcmouse88.harrypotter.domain.entity.Character

class ReadAllCharacterUseCase @Inject constructor(
    private val useCaseRepository: UseCaseRepository
) {

    suspend operator fun invoke(): List<Character>? {
        return useCaseRepository.readAllCharacter()
    }
}