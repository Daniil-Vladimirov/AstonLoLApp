package com.example.astonlolapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateInfo(
    val firstName: String,
    val lastName: String
)
