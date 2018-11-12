package com.example.feature3

import com.example.core.RepositoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feature3RepositoryImpl(val service: Feature3ApiService) : Feature3Repository {

    override fun loadFeature3Entities(callback: RepositoryCallback<List<Planet>, String>) {
        val call = service.getPlanets()
        call.enqueue(object : Callback<PlanetsContainer> {

            override fun onResponse(call: Call<PlanetsContainer>, response: Response<PlanetsContainer>) {
                val body = response.body()
                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onSuccess(body.planets)
                        return
                    }
                }

                callback.onError("Error!")
            }

            override fun onFailure(call: Call<PlanetsContainer>, t: Throwable) {
                callback.onError(t.message ?: "Error")
            }

        })
    }
}