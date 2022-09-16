package com.example.astonlolapp.presentation.screens.heroes_screen

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import androidx.paging.map
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.model.toHero
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.presentation.screens.heroes_screen.adapters.HeroesPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    val useCases: UseCases
) : ViewModel(), HeroesPagingAdapter.Listener {


    val allHeroes = useCases.getAllHeroesUseCase()
    val allFavouriteHeroes = useCases.getAllFavouriteHeroesUseCase()

    override fun onHeroDelete(hero: Hero) {
        //TODO
    }

    override fun addToFavourite(hero: Hero) {
        Timber.d("addHeroClicked")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addHeroAsFavouriteUseCase(hero = hero)
            Timber.d("$hero")
        }
    }

    override fun setColorAsFavourite(view: View, favouriteHero: Hero) {
        viewModelScope.launch {
            allFavouriteHeroes.map { pagingDataFavouriteHero ->
                pagingDataFavouriteHero.map { mappedFavouriteHero ->
                    mappedFavouriteHero.toHero()
                }.filter { hero ->
                    hero == favouriteHero
                }
            }
        }
    }
}
