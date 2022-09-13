package com.example.astonlolapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astonlolapp.domain.model.FavouriteHero

@Dao
interface FavouriteHeroesDao {
    @Query("DELETE FROM favourite_hero_database_table WHERE id=:heroId")
    suspend fun deleteFavouriteHero(heroId: Int)

    @Query("DELETE FROM favourite_hero_database_table")
    suspend fun deleteAllFavouriteHeroes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteHeroes(hero: FavouriteHero)

    @Query("SELECT * FROM favourite_hero_database_table ORDER BY id ASC")
    fun getAllFavoriteHeroes(): PagingSource<Int, FavouriteHero>
}