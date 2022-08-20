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
    fun searchHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<Hero>)

    @Query("DELETE FROM hero_database_table")
    fun deleteAllHeroes()
}