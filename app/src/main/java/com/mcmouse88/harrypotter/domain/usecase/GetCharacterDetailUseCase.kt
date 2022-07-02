package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor (private val repository: UseCaseRepository) {

    operator fun invoke(character: Character, navigation: (Character) -> Unit) {
        return repository.getCharacterDetail(character, navigation)
    }
}