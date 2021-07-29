package com.aya.walmarttask.data.api

import com.aya.walmarttask.data.model.AnimeList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("anime?")
    suspend fun getAnime(@Query("q") p: String): AnimeList

}