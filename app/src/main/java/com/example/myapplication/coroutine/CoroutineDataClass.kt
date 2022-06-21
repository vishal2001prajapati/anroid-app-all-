package com.example.myapplication.coroutine

data class CoroutineDataClassList(val items: ArrayList<CoroutineDataClass>)
data class CoroutineDataClass(val name: String, val description: String, val owner: Owner)
data class Owner(val avatar_url: String)
