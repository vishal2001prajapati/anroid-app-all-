package com.example.myapplication.testpaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PagingActivityViewModel: ViewModel() {

    var retroService: RetroService = RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getListData(): Flow<PagingData<NewUserDetailsList>> {
        return Pager (config = PagingConfig(pageSize = 2, maxSize = 200),
            pagingSourceFactory = {CharacterPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }


}