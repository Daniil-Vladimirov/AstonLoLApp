package com.example.astonlolapp.domain.use_cases.signed_in_state.read_signedin

import com.example.astonlolapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadSignedInUseCase (private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readSignedInState()
    }
}