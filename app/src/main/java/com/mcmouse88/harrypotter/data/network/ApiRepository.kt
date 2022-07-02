package com.mcmouse88.harrypotter.data.network

import com.mcmouse88.harrypotter.domain.entity.Character
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getListCharacterFromApi(): Response<List<Character>> {
        return apiService.getListCharacterFromApi()
    }
}