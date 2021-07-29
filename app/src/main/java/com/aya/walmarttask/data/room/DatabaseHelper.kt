package com.aya.walmarttask.data.room

import com.aya.walmarttask.data.model.Anime

interface DatabaseHelper {

    suspend fun getAnimes(): List<Anime>

    suspend fun insertAll(users: List<Anime>)

}