package com.example.astonlolapp.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val updateInfo: UpdateInfo? = null,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Hero> = emptyList(),
    val comics: List<Comics> = emptyList(),
    val lastUpdated: Long? = null,
    @Transient
    val error: Exception? = null
)