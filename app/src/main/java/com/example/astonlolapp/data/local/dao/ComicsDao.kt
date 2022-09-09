package com.example.astonlolapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astonlolapp.domain.model.Comics
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {

    @Query("SELECT * FROM comics_database_table ORDER BY id ASC")
    fun getComics(): Flow<List<Comics>>

    @Query("DELETE FROM comics_database_table")
    suspend fun deleteAllComics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComics(comics: List<Comics>)
}