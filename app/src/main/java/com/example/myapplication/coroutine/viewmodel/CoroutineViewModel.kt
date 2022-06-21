package com.example.myapplication.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.coroutine.CoroutineDataClassList
import com.example.myapplication.coroutine.RetrofitInstance
import com.example.myapplication.coroutine.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineViewModel: ViewModel() {

    var recyclerListData: MutableLiveData<CoroutineDataClassList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<CoroutineDataClassList> {
        return recyclerListData
    }

    fun apiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            displayData()
        }
    }

    private suspend fun displayData(){
        CoroutineScope(Dispatchers.IO).launch {
            val responseData = async {
                val retroInstance =  RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
                val response =  retroInstance.getData("ny")
                recyclerListData.postValue(response)
            }
           responseData.await()
        }

    }
}