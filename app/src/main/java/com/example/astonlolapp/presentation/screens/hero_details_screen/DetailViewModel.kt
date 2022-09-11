package com.example.astonlolapp.presentation.screens.hero_details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {

    private val _selectedHero = MutableStateFlow<Hero?>(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>("heroId")
            _selectedHero.value = heroId?.let {
                useCases.getSelectedHeroUseCase(heroId = heroId)
            }
            Timber.d(selectedHero.value?.name)
        }

    }

}