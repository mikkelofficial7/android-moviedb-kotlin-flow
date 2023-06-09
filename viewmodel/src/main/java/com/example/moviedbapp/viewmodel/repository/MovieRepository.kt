package com.example.moviedbapp.viewmodel.repository

import com.example.moviedbapp.model.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getAllMovieGenre(): Flow<GenreResponse?>
    suspend fun getMovieByGenre(id: Int, page: Int): Flow<MovieGenreResponse?>
    suspend fun getMovieDetail(id: Int): Flow<MovieDetailResponse?>
    suspend fun getMovieVideo(id: Int): Flow<MovieVideoResponse?>
    suspend fun getMovieReview(id: Int, page: Int): Flow<MovieReviewResponse?>
}