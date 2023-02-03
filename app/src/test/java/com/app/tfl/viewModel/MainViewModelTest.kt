package com.app.tfl.viewModel

import androidx.compose.ui.graphics.Color
import com.app.tfl.RepositoryImpl
import com.app.tfl.api.LineStatusResponse
import com.app.tfl.api.LineStatuses
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `Verify that API call was made`() {
        viewModel.getAll()
        coVerify { repository.getAll() }
    }

    @Test
    fun `get data`() {
        runBlocking {
            coEvery { repository.getAll() } returns (Response.success(_listLine))

            viewModel.getAll()

            assertThat((viewModel.APIState.value as APITask.Response.Ok).payload.size).isGreaterThan(0)
        }
    }

    @Test
    fun `get Line data`() {
        runBlocking {
            coEvery { repository.getAll() } returns (Response.success(_listLine))

            viewModel.getAll()

            assertThat((viewModel.APIState.value as APITask.Response.Ok).payload[0].name).isEqualTo(_line.name)
        }
    }

    @Test
    fun `Validate Line Colour`() {
        runBlocking {
            coEvery { repository.getAll() } returns (Response.success(_listLine))

            viewModel.getAll()

            assertThat((viewModel.APIState.value as APITask.Response.Ok).payload[0].colour).isEqualTo(Color(0xFFFFCE00))
        }
    }
}

val _lineStatuses = LineStatuses(
    statusSeverityDescription = "Service Low",
    reason = "Line being Fixed"
)

val _line = LineStatusResponse(
    name = "Circle",
    lineStatuses = listOf(_lineStatuses),
)

val _listLine = listOf(
    _line,
)