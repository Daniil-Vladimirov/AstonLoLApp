package com.example.astonlolapp.domain.use_cases.user.sign_out

import com.example.astonlolapp.data.repository.Repository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(val repository: Repository) {

    suspend operator fun invoke() {
        repository.signOut()
    }
}