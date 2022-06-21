package com.example.myapplication.apicalling

import com.example.myapplication.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider private constructor() {

    var apiService: ApiService
    var secondApiService: SecondApiService

    companion object {
        private var inst: RetrofitProvider? = null

        fun getInstance(): RetrofitProvider {
            if (inst == null) {
                inst = RetrofitProvider()
            }

            return inst!!
        }
    }

    init {
        // instance of HttpLoggingInterceptor
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.setLevel(HttpLoggingInterceptor.Level.BODY)

        // create client
        val client = OkHttpClient.Builder().addInterceptor(httpLogger).build()

        // Retrofit instance
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        apiService = retrofit.create(ApiService::class.java)
        secondApiService = retrofit.create(SecondApiService::class.java)
    }
}