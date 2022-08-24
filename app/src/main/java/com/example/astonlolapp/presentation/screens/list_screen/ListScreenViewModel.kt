package com.example.astonlolapp.presentation.screens.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.model.ApiResponse
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    useCases: UseCases
) :
    ViewModel() {


    val allHeroes = useCases.getAllHeroesUseCase()


}