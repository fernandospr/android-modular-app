package com.example.feature3

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.RepositoryCallback
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Feature3ViewModelUnitTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var planets : List<Planet>
    private lateinit var repo : Feature3Repository
    private lateinit var viewModel : Feature3ViewModel

    @Before
    fun setup() {
        planets = listOf(Planet("Mars"), Planet("Earth"), Planet("Jupiter"))
        repo = mock()
        viewModel = Feature3ViewModel(repo)
    }

    @Test
    fun getPlanets_shouldEmitLoading() {
        setupRepositoryWithSuccessPlanets()

        val loadingObserver = viewModel.getLoading().testObserver()

        viewModel.getPlanets()

        Assert.assertEquals(listOf(false, true, false), loadingObserver.observedValues)
    }

    @Test
    fun getPlanets_shouldEmitError_whenRepositoryReturnsError() {
        setupRepositoryWithError("Error")

        val errorObserver = viewModel.getError().testObserver()

        viewModel.getPlanets()

        Assert.assertEquals("Error", errorObserver.observedValues.last())
    }

    @Test
    fun getPlanets_shouldEmitPlanets_whenRepositoryReturnsSuccess() {
        setupRepositoryWithSuccessPlanets()

        val planetsObserver = viewModel.getPlanets().testObserver()

        Assert.assertEquals(planets, planetsObserver.observedValues.last())
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