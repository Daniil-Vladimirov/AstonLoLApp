package com.example.astonlolapp.domain.use_cases.save_onboarding

import com.example.astonlolapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class SaveOnboardingStateUseCase(private val repository: Repository) {

    suspend operator fun invoke(state: Boolean) {
       repository.saveOnBoardingState(state = state)
    }
}