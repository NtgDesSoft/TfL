package com.app.tfl.api

import retrofit2.Response
import retrofit2.http.GET

interface StatusEndPoints {
    @GET("Line/Mode/Tube/Status")
    suspend fun getAllLines(): Response<List<LineStatusResponse>>
}