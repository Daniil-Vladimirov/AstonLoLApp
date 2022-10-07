package com.example.astonlolapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperationsAbs {
    suspend fun saveOnBoardingState(state: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>
}