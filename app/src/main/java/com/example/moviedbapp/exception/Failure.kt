package com.example.moviedbapp.exception

sealed class Failure {
    object DataNotFound : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()
    data class Other(val code: String, val message: String) : Failure()
}