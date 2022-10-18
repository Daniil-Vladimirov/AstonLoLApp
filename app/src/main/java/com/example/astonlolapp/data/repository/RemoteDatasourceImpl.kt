package com.example.astonlolapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.astonlolapp.data.local.HeroDatabase
import com.example.astonlolapp.data.paging_source.ComicsSource
import com.example.astonlolapp.data.paging_source.HeroRemoteMediator
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.*
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import com.example.astonlolapp.util.Constants.ITEMS_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl
    (
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase,
) : RemoteDatasourceAbs {

    private val heroDao = heroDatabase.heroDao()


    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagerFactory = { heroDao.getAllHeroes() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PAGE_SIZE, enablePlaceholders = false
            ), pagingSourceFactory = pagerFactory, remoteMediator = HeroRemoteMediator(
                heroApi = heroApi, heroDatabase = heroDatabase
            )
        ).flow
    }


    override fun getComicsFromApi(): Flow<PagingData<Comics>> {
        return Pager(config = PagingConfig(
            pageSize = ITEMS_PAGE_SIZE, enablePlaceholders = false
        ), pagingSourceFactory = {
            ComicsSource(
                heroApi = heroApi, heroDatabase = heroDatabase
            )
        }).flow

    }

    override suspend fun updateHeroes(): Boolean {
        return try {
            val apiResponse = heroApi.getAllHeroes(1, 10)
            val heroes = apiResponse.heroes
            if (heroes.isNotEmpty()) {
                heroDao.addHeroes(heroes)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun tokenVerification(apiRequest: ApiRequest): ApiResponse {
        return try {
            heroApi.tokenVerification(apiRequest = apiRequest)
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun deleteUser(): ApiResponse {
        return try {
            heroApi.deleteUser()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun updateUserInfo(userInfo: UpdateInfo): ApiResponse {
        return try {
            heroApi.updateUserInfo(userInfoUpdate = userInfo)
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun signOut(): ApiResponse {
        return try {
            heroApi.signOut()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun getUser(): ApiResponse {
        return try {
            heroApi.getUser()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

}