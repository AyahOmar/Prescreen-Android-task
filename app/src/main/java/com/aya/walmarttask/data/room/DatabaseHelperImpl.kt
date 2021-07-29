package com.aya.walmarttask.data.room

import com.aya.walmarttask.data.model.Anime

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getAnimes(): List<Anime> = appDatabase.animeDao().getAll()

    override suspend fun insertAll(animes: List<Anime>) = appDatabase.animeDao().insertAll(animes)

}