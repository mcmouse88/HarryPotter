package com.mcmouse88.harrypotter.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository = ApiRepository()
    private val useCaseRepository = UseCaseRepositoryImpl(application)
    private val getCharacterDetailUseCase = GetCharacterDetailUseCase(useCaseRepository)

    private val _listCharacter = MutableLiveData<List<Character>>()
    val listCharacter: LiveData<List<Character>>
        get() = _listCharacter

    fun getListCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getListCharacterFromApi().let {
                if (it.isSuccessful) {
                    _listCharacter.postValue(it.body())
                    Log.d("DATA_TAG", it.body().toString())
                }
            }
        }
    }

    fun getDetailCharacter(navController: NavController, character: Character) {
        getCharacterDetailUseCase.invoke(navController, character)
    }
}