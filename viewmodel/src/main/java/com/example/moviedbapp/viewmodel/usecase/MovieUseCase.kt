package com.example.moviedbapp.viewmodel.usecase

import com.example.moviedbapp.model.*
import com.example.moviedbapp.viewmodel.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieUseCase(
    private val repository: MovieRepository
) {
    suspend fun getAllMovieGenre() : Flow<GenreResponse?> {
        return repository.getAllMovieGenre()
    }

    suspend fun getMovieByGenre(id: Int, page: Int) : Flow<MovieGenreResponse?> {
        return repository.getMovieByGenre(id, page)
    }

    suspend fun getMovieDetail(id: Int) : Flow<MovieDetailResponse?> {
        return repository.getMovieDetail(id)
    }

    suspend fun getMovieVideo(id: Int) : Flow<MovieVideoResponse?> {
        return repository.getMovieVideo(id)
    }

    suspend fun getMovieReview(id: Int, page: Int) : Flow<MovieReviewResponse?> {
        return repository.getMovieReview(id, page)
    }
}