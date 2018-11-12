package com.example.feature3

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.core.RepositoryCallback

class Feature3ViewModel(private val repo: Feature3Repository) : ViewModel() {

    var liveDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    var liveDataEntities: MutableLiveData<List<Planet>> = MutableLiveData()
    var liveDataError: MutableLiveData<String> = MutableLiveData()

    init {
        liveDataLoading.value = false
    }

    fun loadPlanets() {
        liveDataEntities.value = listOf()
        liveDataLoading.value = true
        repo.loadFeature3Entities(object : RepositoryCallback<List<Planet>, String> {
            override fun onSuccess(entities: List<Planet>) {
                liveDataLoading.value = false
                liveDataEntities.value = entities
            }

            override fun onError(errEntity: String) {
                liveDataLoading.value = false
                liveDataError.value = errEntity
            }

        })
    }
}