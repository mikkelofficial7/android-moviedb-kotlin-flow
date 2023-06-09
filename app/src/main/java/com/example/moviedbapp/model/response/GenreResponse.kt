package com.example.moviedbapp.model.response

class GenreResponse(
    val genres : List<Genre>
)

class Genre(
    val id : Int,
    val name: String
)