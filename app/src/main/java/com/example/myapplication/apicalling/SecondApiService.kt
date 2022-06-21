package com.example.myapplication.apicalling

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SecondApiService {

    @GET("api/users")
    fun getUserList(@Query("page") page: String): Call<ListOfUser>
}