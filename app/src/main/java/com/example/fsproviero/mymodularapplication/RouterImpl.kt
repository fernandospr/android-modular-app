package com.example.fsproviero.mymodularapplication

import android.content.Context
import com.example.core.routing.Feature1Router
import com.example.core.routing.Feature2Router
import com.example.core.routing.Feature3Router
import com.example.feature1.Feature1Activity
import com.example.feature2.Feature2Activity
import com.example.feature3.Feature3Activity

class Feature1RouterImpl : Feature1Router {
    override fun showFeature1(context: Context, text: String) {
        val feature1Intent = Feature1Activity.newIntent(context, text)
        context.startActivity(feature1Intent)
    }
}

class Feature2RouterImpl : Feature2Router {
    override fun showFeature2(context: Context, value: Double) {
        val feature2Intent = Feature2Activity.newIntent(context, value)
        context.startActivity(feature2Intent)
    }
}

class Feature3RouterImpl : Feature3Router {
    override fun showFeature3(context: Context) {
        val feature3Intent = Feature3Activity.newIntent(context)
        context.startActivity(feature3Intent)
    }
}