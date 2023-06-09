package com.example.moviedbapp.di.koin

import com.example.moviedbapp.viewmodel.DetailActivityVM
import com.example.moviedbapp.viewmodel.DetailMovieVM
import com.example.moviedbapp.viewmodel.MainActivityVM
import com.example.moviedbapp.viewmodel.ReviewActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {
        val viewModelModule = module(override = true) {
            viewModel { MainActivityVM(get(), get()) }
            viewModel { DetailActivityVM(get(), get()) }
            viewModel { DetailMovieVM(get(), get()) }
            viewModel { ReviewActivityVM(get(), get()) }
        }
    }
}