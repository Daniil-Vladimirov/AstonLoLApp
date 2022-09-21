package com.example.astonlolapp.presentation.screens.heroes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.di.ApplicationScope
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    val useCases: UseCases,
    @ApplicationScope private val iODispatcher: CoroutineDispatcher
) : ViewModel(), HeroesPagingAdapter.Listener {

    val allHeroes = useCases.getAllHeroesUseCase()

    override fun addDeleteFromFavourite(hero: Hero) {
        viewModelScope.launch(iODispatcher) {
            useCases.addHeroAsFavouriteUseCase(hero = hero)
        }
    }

}
