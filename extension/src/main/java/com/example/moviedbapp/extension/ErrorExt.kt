package com.example.moviedbapp.extension

import com.example.moviedbapp.base.exception.Failure
import retrofit2.HttpException
import java.io.IOException

fun Throwable.getGeneralError(): Failure {
    return when (this) {
        is HttpException -> {
            try {
                if (this.code() >= 500) {
                    return Failure.ServerError
                }
                if (this.code() >= 404) {
                    return Failure.DataNotFound
                }
                Failure.Other(this.response()?.code().toString(), this.response()?.message().orEmpty())
            } catch (e: Exception) {
                Failure.NetworkConnection
            }
        }
        is IOException -> Failure.NetworkConnection
        else -> Failure.NetworkConnection
    }
}