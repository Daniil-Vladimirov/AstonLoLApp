package com.example.astonlolapp.presentation.screens.comics_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

   val allComics = useCases.getComicsUseCase()


}