package com.example.fsproviero.mymodularapplication

import com.example.core.routing.Feature1Router
import com.example.core.routing.Feature2Router
import com.example.core.routing.Feature3Router
import org.koin.dsl.module.module

val appModule = module {
    single<Feature1Router> { Feature1RouterImpl() }
    single<Feature2Router> { Feature2RouterImpl() }
    single<Feature3Router> { Feature3RouterImpl() }
}