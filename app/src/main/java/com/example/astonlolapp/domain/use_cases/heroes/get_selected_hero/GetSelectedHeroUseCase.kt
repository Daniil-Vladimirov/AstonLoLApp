package com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Hero

class GetSelectedHeroUseCase(private val repository: Repository) {

    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }

}