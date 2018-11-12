package com.example.fsproviero.appFeature3

import android.content.Context
import android.widget.Toast
import com.example.core.routing.Feature1Router
import com.example.core.routing.Feature3Router
import com.example.feature3.Feature3Activity

class Feature1RouterImpl : Feature1Router {
    override fun showFeature1(context: Context, text: String) {
        Toast.makeText(context, "Opening: $text", Toast.LENGTH_LONG).show()
    }
}

class Feature3RouterImpl : Feature3Router {
    override fun showFeature3(context: Context) {
        val feature3Intent = Feature3Activity.newIntent(context)
        context.startActivity(feature3Intent)
    }
}