package com.example.moviedbapp.koin

import com.example.moviedbapp.network.Api
import org.koin.dsl.module
import retrofit2.Retrofit

class ApiModule {
    companion object {
        val apiModule = module(override = true) {
            single { provideUserApi(get()) }
        }

        fun provideUserApi(retrofit: Retrofit): Api {
            return retrofit.create(Api::class.java)
        }
    }
}