package com.example.myapplication.apicalling

import com.google.gson.annotations.SerializedName

class LoginApiData(
    @SerializedName("token") val token: String
)