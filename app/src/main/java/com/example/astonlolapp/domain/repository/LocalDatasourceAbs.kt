package com.example.astonlolapp.domain.repository

import androidx.paging.PagingData
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.FavouriteHero
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceAbs {
    suspend fun getSelectedHero(heroId: Int): Hero
    fun getComics(): Flow<PagingData<Comics>>
    suspend fun getSelectedComics(comicsId: Int): Comics
    suspend fun deleteFavouriteHero(heroId: Int)
    suspend fun deleteAllFavouriteHeroes()
    suspend fun addFavouriteHeroes(hero: FavouriteHero)
    fun getAllFavouriteHeroes(): Flow<PagingData<FavouriteHero>>
}