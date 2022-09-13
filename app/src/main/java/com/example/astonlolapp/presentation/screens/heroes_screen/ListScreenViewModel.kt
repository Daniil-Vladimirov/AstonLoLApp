package com.example.astonlolapp.presentation.screens.heroes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.model.toFavoriteHero
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.presentation.screens.heroes_screen.adapters.HeroesPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    val useCases: UseCases
) : ViewModel(), HeroesPagingAdapter.Listener {


    val allHeroes = useCases.getAllHeroesUseCase()

    override fun onHeroDelete(hero: Hero) {
        //TODO
    }

    override fun onHeroAdd(hero: Hero) {
        Timber.d("addHeroClicked")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addFavouriteHeroes(hero = hero.toFavoriteHero(hero))
            Timber.d("$hero")
        }
    }
}