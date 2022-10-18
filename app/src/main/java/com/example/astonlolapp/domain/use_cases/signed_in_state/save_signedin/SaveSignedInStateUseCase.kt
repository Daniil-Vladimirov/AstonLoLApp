package com.example.astonlolapp.domain.use_cases.signed_in_state.save_signedin

import com.example.astonlolapp.data.repository.Repository

class SaveSignedInStateUseCase (private val repository: Repository) {

    suspend operator fun invoke(signedIn: Boolean) {
        repository.saveSignedInState(signedIn = signedIn)
    }
}