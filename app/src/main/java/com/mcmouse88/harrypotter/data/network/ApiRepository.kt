package com.mcmouse88.harrypotter.data.network

import com.mcmouse88.harrypotter.domain.entity.Character
import retrofit2.Response

class ApiRepository {

    suspend fun getListCharacterFromApi(): Response<List<Character>> {
        return ApiModel.apiService.getListCharacterFromApi()
    }
}