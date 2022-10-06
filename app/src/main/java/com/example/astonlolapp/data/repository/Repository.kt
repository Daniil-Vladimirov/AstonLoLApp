package com.example.astonlolapp.data.repository

import androidx.paging.PagingData
import com.example.astonlolapp.di.ApplicationScope
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
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


    suspend fun getSelectedHero(heroId: Int): Hero = withContext(iODispatcher) {
        return@withContext localDataSourceAbs.getSelectedHero(heroId = heroId)
    }

    suspend fun getSelectedComics(comicsId: Int): Comics = withContext(iODispatcher) {
        return@withContext localDataSourceAbs.getSelectedComics(comicsId = comicsId)
    }

    suspend fun saveOnBoardingState(state: Boolean) = withContext(iODispatcher) {
        dataStoreOperationsAbs.saveOnBoardingState(state = state)
    }

    suspend fun addHeroAsFavourite(hero: Hero) = withContext(iODispatcher) {
        localDataSourceAbs.addHeroAsFavourite(hero = hero)
    }

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.getAllHeroes()
    }

    fun readBoardingState(): Flow<Boolean> {
        return dataStoreOperationsAbs.readOnBoardingState()
    }

    fun getComicsFromCache(): Flow<PagingData<Comics>> {
        return localDataSourceAbs.getComics()
    }

    fun getComicsFromApi(): Flow<PagingData<Comics>> {
        return remoteDataSourceAbs.getComicsFromApi()
    }

    suspend fun updateHeroes(): Boolean = withContext(iODispatcher) {
        remoteDataSourceAbs.updateHeroes()
    }


}