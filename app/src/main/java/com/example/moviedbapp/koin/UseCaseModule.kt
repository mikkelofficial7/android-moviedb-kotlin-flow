package com.example.moviedbapp.koin

import com.example.moviedbapp.usecase.MovieUseCase
import org.koin.dsl.module

class UseCaseModule {
    companion object {
        internal val useCaseModule = module(override = true) {
            single { MovieUseCase(get()) }
        }
    }
}