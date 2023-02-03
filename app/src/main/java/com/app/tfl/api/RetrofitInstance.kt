package com.app.tfl.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val baseUrl = "https://api.tfl.gov.uk"

    val API: StatusEndPoints by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StatusEndPoints::class.java)
    }
}