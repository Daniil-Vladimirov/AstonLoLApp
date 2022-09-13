package com.example.astonlolapp.domain.use_cases.favorite_heroes.add_favourite_hero

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.FavouriteHero
import javax.inject.Inject

class AddFavouriteHeroUseCase@Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(hero: FavouriteHero) {
       repository.addFavouriteHeroes(hero = hero)
    }
}