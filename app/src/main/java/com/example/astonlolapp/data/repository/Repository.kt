package com.example.astonlolapp.data.repository

import androidx.paging.PagingData
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.LocalDatasourceAbs
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSourceAbs: RemoteDatasourceAbs,
    private val localDataSourceAbs: LocalDatasourceAbs
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.getAllHeroes()
    }

    suspend fun getSelectedHero(heroId: Int): Hero {
        return localDataSourceAbs.getSelectedHero(heroId = heroId)
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteDataSourceAbs.searchHeroes(query = query)
    }
}