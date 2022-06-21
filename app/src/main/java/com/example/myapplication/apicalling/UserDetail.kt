package com.example.myapplication.apicalling

import com.google.gson.annotations.SerializedName

data class UserDetail(
        val avatar: String,
        val email: String,
        @SerializedName("first_name")
        val firstName: String,
        val id: Int,
        @SerializedName("last_name")
        val lastName: String
)
data class User(
        val `data`: UserDetail,
        val support: Support
)