package com.example.astonlolapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astonlolapp.util.Constants
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = Constants.COMICS_DATABASE_TABLE)
data class Comics(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val cover: String,
    val text: List<String>,

)