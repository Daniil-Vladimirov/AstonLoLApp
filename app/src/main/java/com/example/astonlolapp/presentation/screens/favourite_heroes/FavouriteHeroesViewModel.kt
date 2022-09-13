package com.example.astonlolapp.presentation.screens.favourite_heroes

import androidx.lifecycle.ViewModel
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteHeroesViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    val getAllFavouriteHeroes = useCases.getAllFavouriteHeroesUseCase()
}