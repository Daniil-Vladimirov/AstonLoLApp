package com.example.astonlolapp.domain.use_cases.heroes.update_heroes

import com.example.astonlolapp.data.repository.Repository
import timber.log.Timber
import javax.inject.Inject

class UpdateHeroesUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Boolean {
        Timber.d("UpdateHeroes called")
        return repository.updateHeroes()
    }
}