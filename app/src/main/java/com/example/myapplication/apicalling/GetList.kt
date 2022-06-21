package com.example.myapplication.apicalling

import com.google.gson.annotations.SerializedName

data class GetList(
    val `data`: ArrayList<Data>,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val support: Support,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class Data(
    val color: String,
    val id: Int,
    val name: String,
    val pantone_value: String,
    val year: Int
)

data class Support(
    val text: String,
    val url: String
)