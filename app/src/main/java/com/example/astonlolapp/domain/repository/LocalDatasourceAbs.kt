package com.example.astonlolapp.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceAbs {
    suspend fun getSelectedHero(heroId: Int): Hero
    fun getComics(): Flow<PagingData<Comics>>
}