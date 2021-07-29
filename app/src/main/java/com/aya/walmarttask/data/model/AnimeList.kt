package com.aya.walmarttask.data.model

data class AnimeList(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val results: List<Anime>,
    val last_page: Int
)