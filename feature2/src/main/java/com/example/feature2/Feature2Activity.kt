package com.example.feature2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.feature2_activity.*

class Feature2Activity : AppCompatActivity() {

    companion object {
        private const val EXTRA_VALUE = "EXTRA_VALUE"

        fun newIntent(context: Context, value: Double): Intent {
            val intent = Intent(context, Feature2Activity::class.java)
            intent.putExtra(EXTRA_VALUE, value)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature2_activity)
        val value = intent.extras.getDouble(EXTRA_VALUE)
        textView.text = "2.0 to the " + value + " is " + Math.pow(2.toDouble(), value) + " from Feature2Activity"
    }
}
