package com.example.moviedbapp.koin

import android.content.Context
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.helper.NetworkHandler
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
            return OkHttpClient()
                .newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(httpInterceptor())
                .build()
        }

        fun provideRetrofitService(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
    }
}