package com.example.exam7.network

import com.example.exam7.model.FootballMatch
import retrofit2.Response
import retrofit2.http.GET

interface FootballService {
    @GET("v3/48bb936e-261a-4471-a362-3bbb3b9a2a58")
    suspend fun getMatch(): Response<FootballMatch>
}