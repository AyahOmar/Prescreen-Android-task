package com.aya.walmarttask.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * created  by Aya 07/29/2021
 * Retrofit Builder class to connect to the base url and build api layer
 */

object RetrofitBuilder {


    private const val BASE_URL = "https://api.jikan.moe/v3/search/"


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}