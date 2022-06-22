package com.example.myapplication.testpaging


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("api/users")
    suspend fun getResponse(@Query("page") page: Int): RickAndMortyList
}