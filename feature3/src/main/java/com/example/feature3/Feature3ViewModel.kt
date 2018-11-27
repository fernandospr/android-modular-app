package com.example.feature3

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.core.RepositoryCallback

class Feature3ViewModel(private val repo: Feature3Repository) : ViewModel() {

    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()
    private val planets: MutableLiveData<List<Planet>> = MutableLiveData()

    init {
        loading.value = false
        error.value = null
        planets.value = null
    }

    fun getLoading(): LiveData<Boolean> = this.loading
    fun getError(): LiveData<String> = this.error

    fun getPlanets(): LiveData<List<Planet>> {
        if (planets.value == null && loading.value == false) {
            loadPlanets()
        }
        return planets
    }

    fun refreshPlanets() = loadPlanets()

    private fun loadPlanets() {
        loading.value = true
        error.value = null
        planets.value = null
        repo.loadFeature3Entities(object : RepositoryCallback<List<Planet>, String> {
            override fun onSuccess(entities: List<Planet>) {
                loading.value = false
                planets.value = entities
            }

            override fun onError(errEntity: String) {
                loading.value = false
                error.value = errEntity
            }

        })
    }
}