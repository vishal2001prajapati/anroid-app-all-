package com.example.myapplication.apicalling

import com.google.gson.annotations.SerializedName

data class ListOfUser(
    val `data`: List<UserDetailsList>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

data class UserDetailsList(
        val avatar: String,
        val email: String,
        @SerializedName("first_name")
        val firstName: String,
        val id: Int,
        @SerializedName("last_name")
        val lastName: String
)
