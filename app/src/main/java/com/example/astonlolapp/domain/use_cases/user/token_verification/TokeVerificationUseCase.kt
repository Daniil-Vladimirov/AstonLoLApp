package com.example.astonlolapp.domain.use_cases.user.token_verification

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.ApiRequest
import com.example.astonlolapp.domain.model.ApiResponse
import javax.inject.Inject

class TokeVerificationUseCase @Inject constructor(val repository: Repository) {

     suspend operator fun invoke(apiRequest: ApiRequest): ApiResponse {
        return repository.tokenVerification(apiRequest = apiRequest)
    }
}