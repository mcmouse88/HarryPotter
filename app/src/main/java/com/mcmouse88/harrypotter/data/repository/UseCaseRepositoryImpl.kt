package com.mcmouse88.harrypotter.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.navigation.NavController
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.data.room.mapper.DataBaseModelMapper
import com.mcmouse88.harrypotter.data.room.modeldb.DatabaseCharacterModel
import com.mcmouse88.harrypotter.data.room.repository.RoomRepositoryImpl
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import com.mcmouse88.harrypotter.presentation.fragments.MainFragmentDirections

class UseCaseRepositoryImpl(
    application: Application
) : UseCaseRepository {

    private val roomRepository = RoomRepositoryImpl(application)
    private val mapper = DataBaseModelMapper()

    override suspend fun addToFavoriteUseCase(character: Character) {
        val dbModel = mapper.mapEntityToDbModel(character)
        roomRepository.insertCharacter(dbModel)
    }

    override suspend fun deleteFromFavoriteUseCase(character: Character) {
        val dbModel = mapper.mapEntityToDbModel(character)
        roomRepository.deleteCharacter(dbModel)
    }

    override fun getCharacterDetail(navController: NavController, character: Character) {
        navController.navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(character)
        )
    }

    override fun getCharacterFromDbUseCase(name: String): LiveData<Character> {
        return Transformations.map(roomRepository.getCharacterFromDb(name)) {
            mapper.mapdbModelToEntity(it)
        }
    }

    override fun getCharacterListFromDbUseCase(): LiveData<List<Character>> {
        return Transformations.map(roomRepository.allCharacterFromDb) {
            mapper.mapListDbModelToListEntities(it)
        }
    }

}