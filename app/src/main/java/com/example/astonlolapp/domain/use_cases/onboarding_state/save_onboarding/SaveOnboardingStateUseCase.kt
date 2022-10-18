package com.example.astonlolapp.domain.use_cases.onboarding_state.save_onboarding

import com.example.astonlolapp.data.repository.Repository

class SaveOnboardingStateUseCase(private val repository: Repository) {

    suspend operator fun invoke(state: Boolean) {
       repository.saveOnBoardingState(state = state)
    }
}