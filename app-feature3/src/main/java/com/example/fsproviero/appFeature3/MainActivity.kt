package com.example.fsproviero.appFeature3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.core.routing.Feature3Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router : Feature3Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            router.showFeature3(this@MainActivity)
        }
    }
}
