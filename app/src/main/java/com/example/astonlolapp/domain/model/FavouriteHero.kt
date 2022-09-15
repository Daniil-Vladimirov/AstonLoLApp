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

fun Hero.toFavoriteHero(): FavouriteHero {
    return FavouriteHero(
        id = this.id,
        name = this.name,
        image = this.image,
        about = this.about,
        winRate = this.winRate,
        role = this.role,
        ad = this.ad,
        ap = this.ap,
        hp = this.hp,
        mp = this.mp,
        range = this.range,
        abilities = this.abilities,
    )
}

fun FavouriteHero.toHero(): Hero {
    return Hero(
        id = this.id,
        name = this.name,
        image = this.image,
        about = this.about,
        winRate = this.winRate,
        role = this.role,
        ad = this.ad,
        ap = this.ap,
        hp = this.hp,
        mp = this.mp,
        range = this.range,
        abilities = this.abilities,
    )
}
