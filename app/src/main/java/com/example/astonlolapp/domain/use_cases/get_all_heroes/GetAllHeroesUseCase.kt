package com.example.astonlolapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHeroesUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}