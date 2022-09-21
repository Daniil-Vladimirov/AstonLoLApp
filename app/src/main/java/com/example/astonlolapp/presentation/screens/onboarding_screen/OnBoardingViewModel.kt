package com.example.astonlolapp.presentation.screens.onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.di.ApplicationScope
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: UseCases,
    @ApplicationScope private val iODispatcher: CoroutineDispatcher
) : ViewModel() {

    fun saveOnBoardingState(state: Boolean) {
        viewModelScope.launch(iODispatcher) {
            useCases.saveOnboardingStateUseCase(state = state)
        }
    }
}