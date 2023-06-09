package com.example.moviedbapp.di.koin

import android.content.Context
import com.example.moviedbapp.base.helper.NetworkHandler
import com.example.moviedbapp.network.Builder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkModule  {
    companion object {
        private fun httpInterceptor() = HttpLoggingInterceptor().apply {
            return HttpLoggingInterceptor { message ->
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        fun provideNetworkHandler(context: Context) = NetworkHandler(context)

        fun provideOkHttpClient() : OkHttpClient {
            return Builder.initInterceptor(httpInterceptor())
        }

        fun provideRetrofitService(okHttpClient: OkHttpClient): Retrofit {
            return Builder.initRetrofit(okHttpClient)
        }
    }
}