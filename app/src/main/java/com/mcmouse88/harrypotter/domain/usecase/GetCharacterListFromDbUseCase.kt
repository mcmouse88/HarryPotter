package com.mcmouse88.harrypotter.domain.usecase

import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository

class GetCharacterListFromDbUseCase(private val repository: UseCaseRepository) {

    operator fun invoke(): LiveData<List<Character>> {
        return repository.getCharacterListFromDbUseCase()
    }
}