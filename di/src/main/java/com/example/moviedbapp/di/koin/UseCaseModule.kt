package com.example.moviedbapp.di.koin

import com.example.moviedbapp.viewmodel.usecase.MovieUseCase
import org.koin.dsl.module

class UseCaseModule {
    companion object {
        val useCaseModule = module(override = true) {
            single { MovieUseCase(get()) }
        }
    }
}