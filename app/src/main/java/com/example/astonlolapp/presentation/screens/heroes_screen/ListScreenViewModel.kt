package com.example.astonlolapp.presentation.screens.heroes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    val useCases: UseCases,
) : ViewModel(), HeroesPagingAdapter.Listener {
    val allHeroes = useCases.getAllHeroesUseCase()

    override fun addDeleteFromFavourite(hero: Hero) {
        viewModelScope.launch {
            useCases.addHeroAsFavouriteUseCase(hero = hero)
        }
    }

}
