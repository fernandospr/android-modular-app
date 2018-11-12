package com.example.feature1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.feature1_activity.*

class Feature1Activity : AppCompatActivity() {

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun newIntent(context: Context, name: String): Intent {
            val intent = Intent(context, Feature1Activity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature1_activity)
        val name = intent.extras.getString(EXTRA_NAME)
        textView.text = "Hello $name from Feature1Activity"
    }
}
