package com.example.astonlolapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperationsAbs {

    suspend fun insertPreferences(data: Boolean)
    fun readPreferences(): Flow<Boolean>

}