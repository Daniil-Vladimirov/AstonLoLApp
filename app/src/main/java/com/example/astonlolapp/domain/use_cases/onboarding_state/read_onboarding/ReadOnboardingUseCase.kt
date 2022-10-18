package com.example.astonlolapp.domain.use_cases.onboarding_state.read_onboarding

import com.example.astonlolapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readBoardingState()
    }
}