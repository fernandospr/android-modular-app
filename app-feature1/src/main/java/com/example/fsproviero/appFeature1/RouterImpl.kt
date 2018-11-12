package com.example.fsproviero.appFeature1

import android.content.Context
import com.example.core.routing.Feature1Router
import com.example.feature1.Feature1Activity

class Feature1RouterImpl : Feature1Router {
    override fun showFeature1(context: Context, text: String) {
        val feature1Intent = Feature1Activity.newIntent(context, text)
        context.startActivity(feature1Intent)
    }
}
