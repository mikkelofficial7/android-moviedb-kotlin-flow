package com.example.moviedbapp.model

data class MovieVideoResponse (
    val id: Long,
    val results: List<Video>
)

data class Video (
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val published_at: String,
    val id: String
)
