package com.mcmouse88.harrypotter.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.domain.usecase.GetCharacterListFromDbUseCase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UseCaseRepositoryImpl(application)
    private val getCharacterListFromDbUseCase = GetCharacterListFromDbUseCase(repository)

    val listFromDb = getCharacterListFromDbUseCase.invoke()

}