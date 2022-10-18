package com.example.astonlolapp.domain.repository

import androidx.paging.PagingData
import com.example.astonlolapp.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RemoteDatasourceAbs {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun getComicsFromApi(): Flow<PagingData<Comics>>
    suspend fun updateHeroes(): Boolean

    //User calls
    suspend fun tokenVerification(apiRequest: ApiRequest): ApiResponse
    suspend fun deleteUser(): ApiResponse
    suspend fun updateUserInfo(userInfo: UpdateInfo): ApiResponse
    suspend fun signOut(): ApiResponse
    suspend fun getUser(): ApiResponse
}