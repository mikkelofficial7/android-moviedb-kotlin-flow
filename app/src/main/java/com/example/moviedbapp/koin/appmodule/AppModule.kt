package com.example.moviedbapp.koin.appmodule

import com.example.moviedbapp.koin.NetworkModule
import com.example.moviedbapp.MovieDbApp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val mainAppModule = module(override = true) {
    single { NetworkModule.provideOkHttpClient() }
    single { NetworkModule.provideRetrofitService(get()) }
    single { NetworkModule.provideNetworkHandler(androidContext()) }
}