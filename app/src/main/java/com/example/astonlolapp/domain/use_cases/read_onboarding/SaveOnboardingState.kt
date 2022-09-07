package com.example.astonlolapp.domain.use_cases.read_onboarding

import com.example.astonlolapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class SaveOnboardingState(private val repository: Repository) {

    suspend operator fun invoke(state: Boolean) {
       repository.saveOnBoardingState(state = state)
    }
}