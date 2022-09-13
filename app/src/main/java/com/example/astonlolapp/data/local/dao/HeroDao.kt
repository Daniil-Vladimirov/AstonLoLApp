package com.example.astonlolapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astonlolapp.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_database_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_database_table WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM hero_database_table")
    suspend fun deleteAllHeroes()

    /*FavoriteHeroes*/
    @Query("DELETE FROM favourite_hero_database_table WHERE id=:heroId")
    suspend fun deleteFavouriteHero(heroId: Int)

    @Query("DELETE FROM favourite_hero_database_table")
    suspend fun deleteAllFavouriteHeroes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteHeroes(hero: Hero)

    @Query("SELECT * FROM favourite_hero_database_table ORDER BY id ASC")
    fun getAllFavouriteHeroes(): PagingSource<Int, Hero>
}