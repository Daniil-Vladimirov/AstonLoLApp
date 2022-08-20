package com.example.astonlolapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astonlolapp.util.Constants.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class HeroRemoteKey(
    @PrimaryKey
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdate: Long?
)