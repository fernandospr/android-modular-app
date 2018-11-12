package com.example.fsproviero.appFeature1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.core.routing.Feature1Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router : Feature1Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            router.showFeature1(this@MainActivity, editText.text.toString())
        }
    }
}
