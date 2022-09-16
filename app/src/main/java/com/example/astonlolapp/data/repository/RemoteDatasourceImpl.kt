package com.example.astonlolapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.paging_source.ComicsSource
import com.example.astonlolapp.data.paging_source.HeroRemoteMediator
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import com.example.astonlolapp.util.Constants.ITEMS_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl
    (
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
) : RemoteDatasourceAbs {

    private val heroDao = heroDatabase.heroDao()


    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagerFactory = { heroDao.getAllHeroes() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagerFactory,
            remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase
            )
        ).flow
    }


    override fun getComicsFromApi(): Flow<PagingData<Comics>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ComicsSource(
                    heroApi = heroApi,
                    heroDatabase = heroDatabase,
                )
            }
        ).flow

    }

}