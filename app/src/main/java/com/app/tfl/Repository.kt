package com.app.tfl

import com.app.tfl.api.LineStatusResponse
import com.app.tfl.api.StatusEndPoints
import retrofit2.Response
import javax.inject.Inject

interface Repository {
    suspend fun getAll(): Response<List<LineStatusResponse>>
}

class RepositoryImpl @Inject constructor(
    private val remoteData: StatusEndPoints
) : Repository {
    override suspend fun getAll(): Response<List<LineStatusResponse>> {
        return remoteData.getAllLines()
    }
}