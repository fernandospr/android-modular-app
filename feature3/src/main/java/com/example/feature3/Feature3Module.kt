package com.example.feature3

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val feature3Module = module {

    single<Feature3ApiService> { get<Retrofit>().create(Feature3ApiService::class.java) }

    single<Feature3Repository> { Feature3RepositoryImpl(get()) }

    viewModel { Feature3ViewModel(get()) }
}