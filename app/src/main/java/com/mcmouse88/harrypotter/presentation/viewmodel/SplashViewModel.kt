package com.mcmouse88.harrypotter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.harrypotter.data.network.ApiRepository
import com.mcmouse88.harrypotter.domain.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

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
}