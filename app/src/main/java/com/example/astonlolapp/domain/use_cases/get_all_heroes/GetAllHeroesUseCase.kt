package com.example.astonlolapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GetAllHeroesUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        Timber.d("GetAllHeroesIsCalled")
        return repository.getAllHeroes()
    }
}