package com.example.astonlolapp.domain.use_cases

import com.example.astonlolapp.domain.use_cases.comisc.get_comics.GetComicsFromApiUseCase
import com.example.astonlolapp.domain.use_cases.comisc.get_comics.GetComicsUseCase
import com.example.astonlolapp.domain.use_cases.comisc.get_selected_comics.GetSelectedComicsUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.add_favourite_hero.AddFavouriteHeroUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_all_favourite_heroes.DeleteAllFavouriteHeroesUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.delete_favourite_hero.DeleteFavouriteHeroUseCase
import com.example.astonlolapp.domain.use_cases.favorite_heroes.get_all_favourite_heroes.GetAllFavouriteHeroesUseCase
import com.example.astonlolapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.astonlolapp.domain.use_cases.heroes.get_selected_hero.GetSelectedHeroUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.read_onboarding.ReadOnboardingUseCase
import com.example.astonlolapp.domain.use_cases.onboarding.save_onboarding.SaveOnboardingStateUseCase
import com.example.astonlolapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroes: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val getComicsUseCase: GetComicsUseCase,
    val getSelectedComicsUseCase: GetSelectedComicsUseCase,
    val getComicsFromApiUseCase: GetComicsFromApiUseCase,
    val getAllFavouriteHeroesUseCase: GetAllFavouriteHeroesUseCase,
    val deleteFavouriteHeroUseCase: DeleteFavouriteHeroUseCase,
    val deleteAllFavouriteHeroes: DeleteAllFavouriteHeroesUseCase,
    val addFavouriteHeroes: AddFavouriteHeroUseCase
)
