package com.mcmouse88.harrypotter.domain.usecase

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListFromDbUseCase @Inject constructor(private val repository: UseCaseRepository) {

    operator fun invoke(): Flow<List<Character>> {
        return repository.getCharacterListFromDbUseCase()
    }
}