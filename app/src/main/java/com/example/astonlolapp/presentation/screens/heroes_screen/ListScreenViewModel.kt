package com.example.astonlolapp.presentation.screens.heroes_screen

import androidx.lifecycle.ViewModel
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val allHeroes = useCases.getAllHeroesUseCase()
}