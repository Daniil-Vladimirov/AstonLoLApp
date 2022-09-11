package com.example.astonlolapp.domain.repository

import androidx.paging.PagingData
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDatasourceAbs {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
    fun getComicsFromApi(): Flow<PagingData<Comics>>
}