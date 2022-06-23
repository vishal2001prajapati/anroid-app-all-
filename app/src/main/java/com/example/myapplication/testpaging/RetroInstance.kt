package com.example.myapplication.testpaging

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        private const val BASEURL = "https://reqres.in/api/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }
    }
}