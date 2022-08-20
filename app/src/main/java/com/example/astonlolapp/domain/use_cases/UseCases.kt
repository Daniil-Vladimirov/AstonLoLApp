package com.example.astonlolapp.domain.use_cases

import com.example.astonlolapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroes: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
