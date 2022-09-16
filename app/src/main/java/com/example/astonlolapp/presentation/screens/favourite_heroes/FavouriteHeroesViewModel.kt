package com.example.astonlolapp.presentation.screens.favourite_heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteHeroesViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    val getAllFavouriteHeroes = useCases.getAllHeroesUseCase().map {pagingDataHeroes->
        pagingDataHeroes.filter { hero ->
            hero.isFavourite == true
        }
    }

    fun deleteFavouriteHero(heroId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteFavouriteHeroUseCase(heroId)
        }
    }
}