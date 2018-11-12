package com.example.feature3

import com.google.gson.annotations.SerializedName

data class PlanetsContainer(val count: Int, @SerializedName("results") val planets: List<Planet>)

data class Planet(val name: String)