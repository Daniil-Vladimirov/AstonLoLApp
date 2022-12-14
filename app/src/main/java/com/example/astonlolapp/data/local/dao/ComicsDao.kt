package com.example.astonlolapp.data.local.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {

    @Query("SELECT * FROM comics_database_table ORDER BY id ASC")
    fun getComics(): PagingSource<Int, Comics>

    @Query("SELECT * FROM comics_database_table WHERE id=:comicsId")
    suspend fun getSelectedComics(comicsId: Int): Comics

    @Query("DELETE FROM comics_database_table")
    suspend fun deleteAllComics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComics(comics: List<Comics>)
}