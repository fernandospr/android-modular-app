package com.example.feature3

import retrofit2.Call
import retrofit2.http.GET


interface Feature3ApiService {
    @GET("planets")
    fun getPlanets(): Call<PlanetsContainer>
}