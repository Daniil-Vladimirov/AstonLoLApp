package com.example.astonlolapp.domain.use_cases.favorite_heroes.get_all_favourite_heroes

import androidx.paging.PagingData
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.FavouriteHero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouriteHeroesUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<PagingData<FavouriteHero>> {
        return repository.getAllFavouriteHeroes()
    }
}