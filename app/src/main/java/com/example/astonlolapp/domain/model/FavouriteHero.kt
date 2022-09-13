package com.example.astonlolapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astonlolapp.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.FAVOURITE_HERO_DATABASE_TABLE)
data class FavouriteHero(
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

fun Hero.toFavoriteHero(hero: Hero): FavouriteHero {
    return FavouriteHero(
        id = hero.id,
        name = hero.name,
        image = hero.image,
        about = hero.about,
        winRate = hero.winRate,
        role = hero.role,
        ad = hero.ad,
        ap = hero.ap,
        hp = hero.hp,
        mp = hero.mp,
        range = hero.range,
        abilities = hero.abilities,
    )
}