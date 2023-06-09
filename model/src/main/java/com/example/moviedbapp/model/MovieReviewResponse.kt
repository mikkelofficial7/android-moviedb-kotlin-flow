package com.example.moviedbapp.model

data class MovieReviewResponse (
    val id: Long,
    val page: Int,
    val results: List<Review>,
    val total_pages: Long,
    val total_results: Long
)

data class Review (
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)

data class AuthorDetails (
    val name: String,
    val username: String,
    val avatar_path: String,
    val rating: Double? = null
)

