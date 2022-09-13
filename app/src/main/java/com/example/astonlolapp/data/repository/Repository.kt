package com.example.astonlolapp.data.repository

import androidx.paging.PagingData
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.FavouriteHero
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.DataStoreOperationsAbs
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSourceAbs: RemoteDatasourceAbs,
    private val localDataSourceAbs: LocalDatasourceAbs,
    private val dataStoreOperationsAbs: DataStoreOperationsAbs
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.getAllHeroes()
    }

    suspend fun getSelectedHero(heroId: Int): Hero {
        return localDataSourceAbs.getSelectedHero(heroId = heroId)
    }

    suspend fun getSelectedComics(comicsId: Int): Comics {
        return localDataSourceAbs.getSelectedComics(comicsId = comicsId)
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.searchHeroes(query = query)
    }

    suspend fun saveOnBoardingState(state: Boolean) {
        dataStoreOperationsAbs.saveOnBoardingState(state = state)
    }

    fun readBoardingState(): Flow<Boolean> {
        return dataStoreOperationsAbs.readOnBoardingState()
    }

    fun getComics(): Flow<PagingData<Comics>> {
        return localDataSourceAbs.getComics()
    }

    fun getComicsFromApi(): Flow<PagingData<Comics>> {
        return remoteDataSourceAbs.getComicsFromApi()
    }

    suspend fun deleteFavouriteHero(heroId: Int) {
        localDataSourceAbs.deleteFavouriteHero(heroId = heroId)
    }

    suspend fun deleteAllFavouriteHeroes() {
        localDataSourceAbs.deleteAllFavouriteHeroes()
    }

    suspend fun addFavouriteHeroes(hero: FavouriteHero) {
        localDataSourceAbs.addFavouriteHeroes(hero = hero)
    }

    fun getAllFavouriteHeroes(): Flow<PagingData<FavouriteHero>> {
        return localDataSourceAbs.getAllFavouriteHeroes()
    }
}