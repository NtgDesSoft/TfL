package com.app.tfl.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tfl.Repository
import com.app.tfl.api.LineColorMap
import com.app.tfl.data.LineStatus
import com.app.tfl.data.Statuses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _APIState = MutableStateFlow<APITask>(APITask.Init)
    val APIState : StateFlow<APITask> = _APIState.asStateFlow()

    init {
        getAll()
    }

    fun getAll() {
        _APIState.value = APITask.Loading

        viewModelScope.launch {
            val response = repository.getAll()

            if (response.isSuccessful) {
                _APIState.value = APITask.Response.Ok(
                    response.body()?.let { LineStatusResponse ->

                        List(LineStatusResponse.size) {
                            LineStatus(
                                name = LineStatusResponse[it].name,
                                statuses = Statuses(
                                    Severity = LineStatusResponse[it].lineStatuses[0].statusSeverityDescription,
                                    reason = LineStatusResponse[it].lineStatuses[0].reason
                                ),
                                colour = LineColorMap[LineStatusResponse[it].name.lowercase()]?:Color.Unspecified
                            )
                        }
                    } ?: emptyList()
                )
            } else _APIState.value = APITask.Response.Error(response.message())
        }
    }
}