package com.example.moviedbapp.viewmodel.repository

import com.example.moviedbapp.extension.flowOnValue
import com.example.moviedbapp.model.*
import com.example.moviedbapp.network.Api
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val api: Api) : MovieRepository {

    override suspend fun getAllMovieGenre(): Flow<GenreResponse?> {
        return flowOnValue(api.getAllMovieGenre())
    }

    override suspend fun getMovieByGenre(id: Int, page: Int): Flow<MovieGenreResponse?> {
        return flowOnValue(api.getMovieByGenre(id, page = page))
    }

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetailResponse?> {
        return flowOnValue(api.getMovieDetail(id))
    }

    override suspend fun getMovieVideo(id: Int): Flow<MovieVideoResponse?> {
        return flowOnValue(api.getMovieVideoTrailer(id))
    }

    override suspend fun getMovieReview(id: Int, page: Int): Flow<MovieReviewResponse?> {
        return flowOnValue(api.getMovieReview(id, page = page))
    }

}