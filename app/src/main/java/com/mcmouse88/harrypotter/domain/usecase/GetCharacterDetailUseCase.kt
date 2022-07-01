package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class GetCharacterDetailUseCase(private val repository: UseCaseRepository) {

    operator fun invoke(character: Character, navigation: (Character) -> Unit) {
        return repository.getCharacterDetail(character, navigation)
    }
}