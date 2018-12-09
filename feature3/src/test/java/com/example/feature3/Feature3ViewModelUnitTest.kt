package com.example.feature3

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.core.RepositoryCallback
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Feature3ViewModelUnitTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var planets: List<Planet>
    private lateinit var repo: Feature3Repository
    private lateinit var viewModel: Feature3ViewModel
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<String>
    private lateinit var planetsObserver: Observer<List<Planet>>

    @Before
    fun setup() {
        planets = listOf(Planet("Mars"), Planet("Earth"), Planet("Jupiter"))
        repo = mock()
        viewModel = Feature3ViewModel(repo)

        loadingObserver = mock()
        errorObserver = mock()
        planetsObserver = mock()
    }

    @Test
    fun getPlanets_shouldEmitLoading() {
        setupRepositoryWithSuccessPlanets()
        viewModel.getLoading().observeForever(loadingObserver)

        viewModel.getPlanets()

        verify(loadingObserver).onChanged(eq(true))
    }

    @Test
    fun getPlanets_shouldEmitError_whenRepositoryReturnsError() {
        setupRepositoryWithError("Error")
        viewModel.getError().observeForever(errorObserver)

        viewModel.getPlanets()

        verify(errorObserver).onChanged(eq("Error"))
    }

    @Test
    fun getPlanets_shouldEmitPlanets_whenRepositoryReturnsSuccess() {
        setupRepositoryWithSuccessPlanets()
        viewModel.getPlanets().observeForever(planetsObserver)

        verify(planetsObserver).onChanged(eq(planets))
    }

    @Test
    fun getPlanets_shouldEmitLoading_thenLoadFromRepository_thenEmitPlanets() {
        setupRepositoryWithSuccessPlanets()
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getPlanets().observeForever(planetsObserver)

        inOrder(loadingObserver, planetsObserver, repo) {
            verify(loadingObserver).onChanged(eq(true))
            verify(repo).loadFeature3Entities(any())
            verify(planetsObserver).onChanged(eq(planets))
        }
    }

    @Test
    fun getPlanets_shouldLoadPlanetsFromRepositoryOnlyOnce_whenRepositoryReturnsSuccess() {
        setupRepositoryWithSuccessPlanets()

        viewModel.getPlanets()
        viewModel.getPlanets()

        verify(repo, times(1)).loadFeature3Entities(any())
    }

    @Test
    fun getPlanets_shouldLoadPlanetsFromRepositoryAgain_whenRepositoryReturnedError() {
        setupRepositoryWithError("Error")

        viewModel.getPlanets()
        viewModel.getPlanets()

        verify(repo, times(2)).loadFeature3Entities(any())
    }

    private fun setupRepositoryWithSuccessPlanets() {
        doAnswer {
            val callback: RepositoryCallback<List<Planet>, String> = it.getArgument(0)
            callback.onSuccess(planets)
        }.whenever(repo).loadFeature3Entities(any())
    }

    private fun setupRepositoryWithError(error: String) {
        doAnswer {
            val callback: RepositoryCallback<List<Planet>, String> = it.getArgument(0)
            callback.onError(error)
        }.whenever(repo).loadFeature3Entities(any())
    }
}