package com.example.astonlolapp.domain.use_cases.user.delete_user

import com.example.astonlolapp.data.repository.Repository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(val repository: Repository) {

    suspend operator fun invoke() {
        repository.deleteUser()
    }
}