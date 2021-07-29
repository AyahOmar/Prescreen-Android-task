package com.aya.walmarttask.data.repository

import com.aya.walmarttask.data.api.ApiHelper
import com.aya.walmarttask.data.model.Anime
import com.aya.walmarttask.data.room.DatabaseHelper

/**
 * created  by Aya 07/29/2021
 * MainRepository class that is works with api helper ad dp helper to use in model view
 */

class MainRepository (private val apiHelper: ApiHelper,private val dbHelper: DatabaseHelper) {

    suspend fun getAnime(q: String) = apiHelper.getAnime(q)

    suspend fun getAnimeDB(): List<Anime> = dbHelper.getAnimes()
    suspend fun insertANimeDB(animes: List<Anime>) = dbHelper.insertAll(animes)
}