package com.app.tfl.api

//import org.mockito.MockitoAnnotations
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: StatusEndPoints
    lateinit var gson: Gson

    @Before
    fun setup() {
        //MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(StatusEndPoints::class.java)
    }

    @Test
    fun `get all movie api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getAllLines()
            val request = mockWebServer.takeRequest()
            assertEquals("", request.path)
            assertEquals(true, response.body()?.isEmpty() )
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}