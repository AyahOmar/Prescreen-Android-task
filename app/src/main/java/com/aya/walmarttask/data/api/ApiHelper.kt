package com.aya.walmarttask.data.api

class ApiHelper(private val apiService: ApiService ) {

    suspend fun getAnime( query: String) = apiService.getAnime(query)
}