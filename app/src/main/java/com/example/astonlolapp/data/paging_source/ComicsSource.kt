package com.example.astonlolapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Comics
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class ComicsSource (
    private val heroApi: HeroApi,
    heroDatabase: HeroDatabase,
    val iODispatcher: CoroutineDispatcher
) : PagingSource<Int, Comics>() {

    private val comicsDao = heroDatabase.comicsDao()
    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Comics> {

        return try {
            val apiResponse = heroApi.getComics()
            val comics = apiResponse.comics
            withContext(iODispatcher) {
                comicsDao.addComics(comics)
            }


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