package com.example.astonlolapp.domain.use_cases.user.get_user

import com.example.astonlolapp.data.repository.Repository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(val repository: Repository) {

    suspend operator fun invoke() {
        repository.getUser()
    }
}