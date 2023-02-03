package com.app.tfl.viewModel

import com.app.tfl.data.LineStatus

sealed class APITask{
    object Init: APITask()
    object Loading: APITask()
    sealed class Response: APITask(){
        data class Error(val error:String): Response()
        data class Ok(val payload:List<LineStatus>): Response()
    }
}