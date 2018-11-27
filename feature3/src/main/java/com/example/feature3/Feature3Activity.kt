package com.example.feature3

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.core.routing.Feature1Router
import kotlinx.android.synthetic.main.feature3_activity.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class Feature3Activity: AppCompatActivity(), Feature3Adapter.Listener {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, Feature3Activity::class.java)
            return intent
        }
    }

    private lateinit var adapter: Feature3Adapter
    private val featureViewModel: Feature3ViewModel by viewModel()
    private val router: Feature1Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature3_activity)

        adapter = Feature3Adapter()
        adapter.setListener(this)
        myListView.adapter = adapter
        myListView.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            featureViewModel.refreshPlanets()
        }

        featureViewModel.getLoading().observe(this, Observer { isLoading ->
            isLoading?.let { showLoadingView(it) }
        })
        featureViewModel.getError().observe(this, Observer { error ->
            error?.let { showErrorView(it) }
        })
        featureViewModel.getPlanets().observe(this, Observer { entities ->
            entities?.let { showEntities(it) }
        })
    }

    private fun showEntities(entities: List<Planet>) {
        adapter.setEntities(entities)
    }

    private fun showErrorView(description: String) {
        Toast.makeText(this, description, Toast.LENGTH_LONG).show()
    }

    private fun showLoadingView(show: Boolean) {
        if (show) {
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.INVISIBLE
        }
    }

    override fun onPlanetClick(planet: Planet) {
        router.showFeature1(this, planet.name)
    }

}