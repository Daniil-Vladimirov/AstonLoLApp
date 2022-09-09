package com.example.astonlolapp.domain.repository

import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceAbs {
    suspend fun getSelectedHero(heroId:Int):Hero
   fun getComics(): Flow<List<Comics>>
}