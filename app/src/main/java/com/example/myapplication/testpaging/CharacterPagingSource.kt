package com.example.myapplication.testpaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.apicalling.UserDetailsList

class CharacterPagingSource(private val apiService: RetroService) : PagingSource<Int, NewUserDetailsList>() {


    override fun getRefreshKey(state: PagingState<Int, NewUserDetailsList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewUserDetailsList> {

        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getResponse(page = 1,params.loadSize)
            LoadResult.Page(data = response.info.data, prevKey = if (nextPage == 1) null else nextPage.minus(1), nextKey = if (response.info.data.isEmpty()) null else nextPage.plus(1))
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }

    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}