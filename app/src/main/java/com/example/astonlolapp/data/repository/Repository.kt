package com.example.astonlolapp.data.repository

import androidx.paging.PagingData
import com.example.astonlolapp.di.ApplicationScope
import com.example.astonlolapp.domain.model.*
import com.example.astonlolapp.domain.repository.DataStoreOperationsAbs
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSourceAbs: RemoteDatasourceAbs,
    private val localDataSourceAbs: LocalDatasourceAbs,
    private val dataStoreOperationsAbs: DataStoreOperationsAbs,
    @ApplicationScope private val iODispatcher: CoroutineDispatcher
) {

    //Operations with heroes
    suspend fun getSelectedHero(heroId: Int): Hero = withContext(iODispatcher) {
        return@withContext localDataSourceAbs.getSelectedHero(heroId = heroId)
    }

    suspend fun addHeroAsFavourite(hero: Hero) = withContext(iODispatcher) {
        localDataSourceAbs.addHeroAsFavourite(hero = hero)
    }

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.getAllHeroes()
    }

    suspend fun updateHeroes(): Boolean = withContext(iODispatcher) {
        remoteDataSourceAbs.updateHeroes()
    }

    //Operations with comics
    suspend fun getSelectedComics(comicsId: Int): Comics = withContext(iODispatcher) {
        return@withContext localDataSourceAbs.getSelectedComics(comicsId = comicsId)
    }

    fun getComicsFromCache(): Flow<PagingData<Comics>> {
        return localDataSourceAbs.getComics()
    }

    fun getComicsFromApi(): Flow<PagingData<Comics>> {
        return remoteDataSourceAbs.getComicsFromApi()
    }

    //Operations with dataStore
    suspend fun saveOnBoardingState(state: Boolean) = withContext(iODispatcher) {
        dataStoreOperationsAbs.saveOnBoardingState(state = state)
    }

    fun readBoardingState(): Flow<Boolean> {
        return dataStoreOperationsAbs.readOnBoardingState()
    }

    suspend fun saveSignedInState(signedIn: Boolean) = withContext(iODispatcher) {
        dataStoreOperationsAbs.saveSignedInState(signedIn = signedIn)
    }

    fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperationsAbs.readSignedInState()
    }

    //Operations with User

    suspend fun tokenVerification(apiRequest: ApiRequest): ApiResponse = withContext(iODispatcher) {
        remoteDataSourceAbs.tokenVerification(apiRequest = apiRequest)
    }


    suspend fun deleteUser(): ApiResponse {
        return remoteDataSourceAbs.deleteUser()
    }

    suspend fun updateUserInfo(userInfo: UpdateInfo): ApiResponse {
        return remoteDataSourceAbs.updateUserInfo(userInfo = userInfo)
    }

    suspend fun signOut(): ApiResponse {
        return remoteDataSourceAbs.signOut()
    }

    suspend fun getUser(): ApiResponse {
        return remoteDataSourceAbs.getUser()
    }

}