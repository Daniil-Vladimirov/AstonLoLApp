package com.example.astonlolapp.presentation.screens.comics_screen

import androidx.lifecycle.ViewModel
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {


    var comics = useCases.getComicsUseCase()
    val comicsApi = useCases.getComicsFromApiUseCase()

}



