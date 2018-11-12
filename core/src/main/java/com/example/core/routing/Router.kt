package com.example.core.routing

import android.content.Context

interface Feature1Router {
    fun showFeature1(context: Context, text: String)
}

interface Feature2Router {
    fun showFeature2(context: Context, value: Double)
}

interface Feature3Router {
    fun showFeature3(context: Context)
}