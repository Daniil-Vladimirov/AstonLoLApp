package com.example.astonlolapp.domain.use_cases

import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromApiUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_comics.GetComicsFromCacheUseCase
import com.example.astonlolapp.domain.use_cases.comics.get_selected_comics.GetSelectedComicsUseCase
import com.example.astonlolapp.domain.use_cases.heroes.add_hero_as_favourite.AddHeroAsFavouriteUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.save_onboarding.SaveOnboardingStateUseCase

data class UseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val getComicsFromCacheUseCase: GetComicsFromCacheUseCase,
    val getSelectedComicsUseCase: GetSelectedComicsUseCase,
    val getComicsFromApiUseCase: GetComicsFromApiUseCase,
    val addHeroAsFavouriteUseCase: AddHeroAsFavouriteUseCase
)
