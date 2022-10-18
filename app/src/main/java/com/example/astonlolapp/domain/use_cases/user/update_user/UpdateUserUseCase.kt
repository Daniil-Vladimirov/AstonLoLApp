package com.example.astonlolapp.domain.use_cases.user.update_user

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.UpdateInfo
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(val repository: Repository) {

    suspend operator fun invoke(userInfo: UpdateInfo) {
        repository.updateUserInfo(userInfo = userInfo)
    }
}