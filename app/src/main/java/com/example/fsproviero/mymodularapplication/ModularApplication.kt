package com.example.fsproviero.mymodularapplication

import android.app.Application
import com.example.core.coreModule
import com.example.feature3.feature3Module
import org.koin.android.ext.android.startKoin

class ModularApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, coreModule, feature3Module))
    }
}