package com.example.moviedbapp.network

import com.example.moviedbapp.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("genre/movie/list")
    suspend fun getAllMovieGenre(@Query("api_key") apikey: String = BuildConfig.API_KEY): GenreResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getMovieByGenre(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en",
        @Query("page") page: Int,
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): MovieGenreResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): MovieDetailResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideoTrailer(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): MovieVideoResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): MovieReviewResponse
}