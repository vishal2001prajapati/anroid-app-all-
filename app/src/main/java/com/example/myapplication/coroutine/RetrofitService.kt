package com.example.myapplication.coroutine

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    suspend fun getData(@Query("q") query: String): CoroutineDataClassList

}