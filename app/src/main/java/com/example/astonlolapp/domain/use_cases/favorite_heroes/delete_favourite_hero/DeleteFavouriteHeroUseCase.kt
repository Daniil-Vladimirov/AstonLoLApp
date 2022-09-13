package com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_favourite_hero

import com.example.astonlolapp.data.repository.Repository
import javax.inject.Inject

class DeleteFavouriteHeroUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(heroId: Int) {
        return repository.deleteFavouriteHero(heroId = heroId)
    }
}