package com.example.astonlolapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Comics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

typealias ComicsPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Comics>

class ComicsSource(
    private val heroApi: HeroApi,
     heroDatabase: HeroDatabase,
) : PagingSource<Int, Comics>() {

    private val comicsDao = heroDatabase.comicsDao()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comics> {

        val pageIndex = params.key ?: 0

        return try {
            Timber.d("Comics Source is calle")
            val apiResponse = heroApi.getComics()
            val comics = apiResponse.comics
               withContext(Dispatchers.IO) {
                   comicsDao.addComics(comics)
               }


            if (comics.isNotEmpty()) {
                Timber.d("comics are not empty")
                LoadResult.Page(
                    data = comics,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                Timber.d("comics are empty")
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            Timber.d("$e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comics>): Int? {
        return state.anchorPosition
    }

    /*private suspend fun cacheComics(apiResponse: ApiResponse): List<Comics> =
        withContext(Dispatchers.IO) {
            val comics = apiResponse.comics
            comicsDao.addComics(comics)
            return@withContext comicsDao.getComics()
        }*/

}