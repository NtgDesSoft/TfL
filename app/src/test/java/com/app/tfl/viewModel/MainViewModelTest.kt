package com.app.tfl.viewModel

import com.app.tfl.RepositoryImpl
import com.app.tfl.api.LineStatusResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MainViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var viewModel: MainViewModel
    lateinit var repository: RepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `get data`() {
        runBlocking {
            coEvery { repository.getAll() } returns (Response.success(lineStatuses))

            viewModel.getAll()

            assertThat((viewModel.APIState.value as APITask.Response.Ok).payload.size).isGreaterThan(0)
        }
    }

    @Test
    fun `get Line data`() {
        runBlocking {
            coEvery { repository.getAll() } returns (Response.success(lineStatuses))

            viewModel.getAll()

            assertThat((viewModel.APIState.value as APITask.Response.Ok).payload[0].name).isEqualTo(lineStatuses)
        }
    }

}

val lineStatuses = listOf<LineStatusResponse>()

