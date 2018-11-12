package com.example.fsproviero.mymodularapplication

import com.example.core.routing.Feature1Router
import com.example.core.routing.Feature3Router
import com.example.fsproviero.appFeature3.Feature1RouterImpl
import com.example.fsproviero.appFeature3.Feature3RouterImpl
import org.koin.dsl.module.module

val featureAppModule = module {
    single<Feature1Router> { Feature1RouterImpl() }
    single<Feature3Router> { Feature3RouterImpl() }
}