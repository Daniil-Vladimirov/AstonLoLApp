package com.example.astonlolapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero

class ComicsSource(
    private val heroApi: HeroApi,
) : PagingSource<Int, Comics>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comics> {
        return try {
            val apiResponse = heroApi.getComics()
            val comics = apiResponse.comics
            if (comics.isNotEmpty()) {
                LoadResult.Page(
                    data = comics,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comics>): Int? {
        return state.anchorPosition
    }
}