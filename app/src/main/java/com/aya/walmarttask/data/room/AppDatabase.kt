package com.aya.walmarttask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aya.walmarttask.data.Dao.AnimeDao
import com.aya.walmarttask.data.model.Anime


@Database(entities = [Anime::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

}