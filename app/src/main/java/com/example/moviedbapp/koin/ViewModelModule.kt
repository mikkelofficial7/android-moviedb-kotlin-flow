package com.example.moviedbapp.koin

import com.example.moviedbapp.ui.detailmovie.DetailMovieVM
import com.example.moviedbapp.ui.detailpage.DetailActivityVM
import com.example.moviedbapp.ui.mainpage.MainActivityVM
import com.example.moviedbapp.ui.reviewrating.ReviewActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {
        internal val viewModelModule = module(override = true) {
            viewModel { MainActivityVM(get(), get()) }
            viewModel { DetailActivityVM(get(), get()) }
            viewModel { DetailMovieVM(get(), get()) }
            viewModel { ReviewActivityVM(get(), get()) }
        }
    }
}