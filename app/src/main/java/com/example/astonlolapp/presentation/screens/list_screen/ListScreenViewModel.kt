package com.example.astonlolapp.presentation.screens.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {


    val allHeroes = useCases.getAllHeroesUseCase().cachedIn(viewModelScope)


}