package com.example.feature3

import com.example.core.RepositoryCallback

interface Feature3Repository {
    fun loadFeature3Entities(callback: RepositoryCallback<List<Planet>, String>)
}

