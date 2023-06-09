package com.example.moviedbapp.model

data class MovieDetailResponse (
    val adult: Boolean,
    val backdrop_path: Any? = null,
    val belongs_to_collection: Any? = null,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<Any?>,
    val production_countries: List<Any?>,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<Any?>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
)
