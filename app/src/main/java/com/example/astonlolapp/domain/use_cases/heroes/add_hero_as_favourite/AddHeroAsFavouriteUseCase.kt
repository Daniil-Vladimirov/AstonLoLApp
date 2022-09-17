package com.example.astonlolapp.domain.use_cases.heroes.add_hero_as_favourite

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Hero
import javax.inject.Inject

class AddHeroAsFavouriteUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(hero: Hero) {
        repository.addHeroAsFavourite(
            hero = hero.copy(isFavourite = hero.isFavourite != true))
    }
}