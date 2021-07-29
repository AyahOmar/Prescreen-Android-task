package com.aya.walmarttask.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anime(
    @PrimaryKey val mal_id: Int,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "image_url") val image_url: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "airing") val airing: Boolean?,
    @ColumnInfo(name = "synopsis")val synopsis: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "episodes") val episodes: Int?,
    @ColumnInfo(name = "score") val score: Double?,
    @ColumnInfo(name = "start_date") val start_date: String?,
    @ColumnInfo(name = "end_date") val end_date: String?,
    @ColumnInfo(name = "members") val members: Int?,
    @ColumnInfo(name = "rated") val rated: String?
)