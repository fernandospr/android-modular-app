package com.example.core

interface RepositoryCallback<T, E> {
    fun onSuccess(entity: T)
    fun onError(errEntity: E)
}