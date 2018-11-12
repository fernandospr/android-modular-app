package com.example.fsproviero.appFeature3

import android.app.Application
import com.example.fsproviero.mymodularapplication.featureAppModule
import org.koin.android.ext.android.startKoin

class FeatureApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(featureAppModule))
    }
}