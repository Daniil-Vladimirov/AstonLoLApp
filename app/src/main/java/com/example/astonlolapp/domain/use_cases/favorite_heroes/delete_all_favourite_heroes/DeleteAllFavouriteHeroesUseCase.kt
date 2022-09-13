package com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_all_favourite_heroes

import com.example.astonlolapp.data.repository.Repository
import javax.inject.Inject

class DeleteAllFavouriteHeroesUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() {
        return repository.deleteAllFavouriteHeroes()
    }
}