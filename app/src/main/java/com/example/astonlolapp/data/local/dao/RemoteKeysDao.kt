package com.example.astonlolapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astonlolapp.domain.model.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM remote_keys_table WHERE id=:heroId")
    suspend fun getRemoteKey(heroId: Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}