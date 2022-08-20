package com.example.astonlolapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Hero
import javax.inject.Inject

class SearchHeroSource @Inject constructor(
    private val heroApi: HeroApi,
    private val searchQuery: String
) : PagingSource<Int, Hero>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {

        try {
            val apiResponse = heroApi.searchHeroes(searchQuery)
            val heroes = apiResponse.heroes
            return if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
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
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }
}