package com.aya.walmarttask.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aya.walmarttask.data.model.Anime

/**
 * created  by Aya 07/29/2021
 * Data base queries class
 */

@Dao
interface AnimeDao {

    @Query("SELECT * FROM Anime")
    suspend fun getAll(): List<Anime>

    @Insert
    suspend fun insertAll(users: List<Anime>)

}