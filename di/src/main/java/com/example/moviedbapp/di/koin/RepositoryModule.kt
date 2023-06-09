package com.example.moviedbapp.di.koin

import com.example.moviedbapp.viewmodel.repository.MovieRepository
import com.example.moviedbapp.viewmodel.repository.MovieRepositoryImpl
import org.koin.dsl.module

class RepositoryModule {
    companion object {
        val repositoryModule = module(override = true) {
            single<MovieRepository> { return@single MovieRepositoryImpl(get()) }
        }
    }
}