package com.example.fsproviero.mymodularapplication

import com.example.core.routing.Feature1Router
import com.example.fsproviero.appFeature1.Feature1RouterImpl
import org.koin.dsl.module.module

val featureAppModule = module {
    single<Feature1Router> { Feature1RouterImpl() }
}