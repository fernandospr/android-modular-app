package com.example.fsproviero.mymodularapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.core.routing.Feature1Router
import com.example.core.routing.Feature2Router
import com.example.core.routing.Feature3Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router1: Feature1Router by inject()
    private val router2: Feature2Router by inject()
    private val router3: Feature3Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonFeature1.setOnClickListener {
            router1.showFeature1(this@MainActivity, "Fer")
        }
        buttonFeature2.setOnClickListener {
            router2.showFeature2(this@MainActivity, 4.toDouble())
        }
        buttonFeature3.setOnClickListener {
            router3.showFeature3(this@MainActivity)
        }
    }
}
