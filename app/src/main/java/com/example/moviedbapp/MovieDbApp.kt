package com.example.moviedbapp

import android.app.Application
import com.example.moviedbapp.koin.ApiModule
import com.example.moviedbapp.koin.RepositoryModule
import com.example.moviedbapp.koin.UseCaseModule
import com.example.moviedbapp.koin.ViewModelModule
import com.example.moviedbapp.koin.appmodule.mainAppModule
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
            modules(mainAppModule, ApiModule.apiModule, RepositoryModule.repositoryModule,
                UseCaseModule.useCaseModule, ViewModelModule.viewModelModule
            )
        }
    }

}