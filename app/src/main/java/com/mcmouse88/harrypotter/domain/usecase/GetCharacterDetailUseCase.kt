package com.mcmouse88.harrypotter.domain.usecase

import androidx.navigation.NavController
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class GetCharacterDetailUseCase(private val repository: UseCaseRepository) {

    operator fun invoke(navController: NavController, character: Character) {
        return repository.getCharacterDetail(navController, character)
    }
}