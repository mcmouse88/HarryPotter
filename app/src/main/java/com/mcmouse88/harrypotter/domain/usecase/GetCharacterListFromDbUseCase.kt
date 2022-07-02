package com.mcmouse88.harrypotter.domain.usecase

import androidx.lifecycle.LiveData
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import javax.inject.Inject

class GetCharacterListFromDbUseCase @Inject constructor(private val repository: UseCaseRepository) {

    operator fun invoke(): LiveData<List<Character>> {
        return repository.getCharacterListFromDbUseCase()
    }
}