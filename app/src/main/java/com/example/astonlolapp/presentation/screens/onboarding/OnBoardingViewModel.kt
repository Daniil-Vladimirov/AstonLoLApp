package com.example.astonlolapp.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    fun saveOnBoardingState(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnboardingStateUseCase(state = state)
        }
            Timber.d("set to true")
    }
}