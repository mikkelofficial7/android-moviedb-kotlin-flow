package com.example.moviedbapp

import android.app.Application
import com.example.moviedbapp.di.koin.ApiModule
import com.example.moviedbapp.di.koin.RepositoryModule
import com.example.moviedbapp.di.koin.UseCaseModule
import com.example.moviedbapp.di.koin.ViewModelModule
import com.example.moviedbapp.di.koin.appmodule.MainAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieDbApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieDbApp)
            modules(MainAppModule, ApiModule.apiModule, RepositoryModule.repositoryModule,
                UseCaseModule.useCaseModule, ViewModelModule.viewModelModule
            )
        }
    }

}