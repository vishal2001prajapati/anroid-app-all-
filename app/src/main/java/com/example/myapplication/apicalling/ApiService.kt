package com.example.myapplication.apicalling

import com.example.myapplication.apicalling.request.LoginRequest
import com.example.myapplication.util.END_POINT_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT_URL)
    fun getList(): Call<GetList>

    @GET("api/users/{id}")
    fun getUserDetails(@Path("id") id: String): Call<User>

    @POST("api/login")
    fun  loginUser(@Body loginRequest: LoginRequest): Call<LoginApiData>

}