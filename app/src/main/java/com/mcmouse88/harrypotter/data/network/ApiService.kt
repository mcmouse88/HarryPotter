package com.mcmouse88.harrypotter.data.network

import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getListCharacterFromApi(): Response<List<Character>>
}