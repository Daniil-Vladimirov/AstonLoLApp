package com.example.astonlolapp.domain.use_cases

import com.example.astonlolapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.save_onboarding.SaveOnboardingStateUseCase
import com.example.astonlolapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroes: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase
)
