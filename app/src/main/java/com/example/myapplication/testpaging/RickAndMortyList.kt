package com.example.myapplication.testpaging

import com.example.myapplication.apicalling.Support
import com.google.gson.annotations.SerializedName

data class RickAndMortyList(val info: ListOfUser, val results: List<NewUserDetailsList> )

data class NewUserDetailsList(
    val avatar: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)

data class ListOfUser(
    val `data`: List<NewUserDetailsList>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)
