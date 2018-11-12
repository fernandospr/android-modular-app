package com.example.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 10L

val coreModule = module {

    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single<OkHttpClient> {
        OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(get<HttpLoggingInterceptor>())
                    }
                }
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<Retrofit> {
        Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(get())
                .addConverterFactory(get<GsonConverterFactory>())
                .build()
    }

}