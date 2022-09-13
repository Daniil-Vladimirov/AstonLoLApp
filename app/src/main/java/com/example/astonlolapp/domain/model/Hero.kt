package com.example.astonlolapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astonlolapp.util.Constants.HERO_DATABASE_TABLE
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = HERO_DATABASE_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val winRate: Double,
    val role: String,
    val ad: Int,
    val ap: Int,
    val hp: Int,
    val mp: Int,
    val range: Boolean,
    val abilities: List<String>,
)