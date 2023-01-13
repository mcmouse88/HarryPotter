package com.mcmouse88.harrypotter.data.repository

import com.mcmouse88.harrypotter.data.network.ApiService
import com.mcmouse88.harrypotter.data.room.mapper.DataBaseModelMapper
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import com.mcmouse88.harrypotter.utils.AppExceptions.EmptyBodyException
import com.mcmouse88.harrypotter.utils.wrapAppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UseCaseRepositoryImpl @Inject constructor(
    private val roomRepository: RoomRepository,
    private val apiService: ApiService,
    private val mapper: DataBaseModelMapper
) : UseCaseRepository {

    override suspend fun readAllCharacter(): List<Character> = wrapAppException {
        val response = apiService.getListCharacterFromApi()
        if (response.body() == null) throw EmptyBodyException()
        return@wrapAppException response.body()!!
    }

    override suspend fun addToFavoriteUseCase(character: Character) {
        val dbModel = mapper.mapEntityToDbModel(character)
        roomRepository.insertCharacter(dbModel)
    }

    override suspend fun deleteFromFavoriteUseCase(character: Character) {
        val dbModel = mapper.mapEntityToDbModel(character)
        roomRepository.deleteCharacter(dbModel)
    }

    override fun getCharacterDetail(character: Character, navigation: (Character) -> Unit) {
        navigation(character)
    }

    override suspend fun checkCharacterFromDbUseCase(name: String): Boolean {
        return roomRepository.checkCharacterFromDb(name)
    }

    override fun getCharacterListFromDbUseCase(): Flow<List<Character>> {
        return callbackFlow {
            roomRepository.allCharacterFromDb.collect { list ->
                send(mapper.mapListDbModelToListEntities(list))
            }
        }
    }
}