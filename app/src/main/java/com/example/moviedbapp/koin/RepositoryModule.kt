package com.example.moviedbapp.koin

import com.example.moviedbapp.repository.MovieRepository
import com.example.moviedbapp.repository.MovieRepositoryImpl
import org.koin.dsl.module

class RepositoryModule {
    companion object {
        val repositoryModule = module(override = true) {
            single<MovieRepository> { return@single MovieRepositoryImpl(get()) }
        }
    }
}