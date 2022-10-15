package com.example.astonlolapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.astonlolapp.domain.repository.DataStoreOperationsAbs
import com.example.astonlolapp.util.Constants.HERO_PREFERENCES_NAME
import com.example.astonlolapp.util.Constants.ON_BOARDING_PREFERENCES_KEY
import com.example.astonlolapp.util.Constants.SIGNED_IN_PREFERENCES_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = HERO_PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperationsAbs {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = ON_BOARDING_PREFERENCES_KEY)
        val signedInKey = booleanPreferencesKey(name = SIGNED_IN_PREFERENCES_KEY)
    }


    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = state
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {

        return dataStore.data
            .catch { exception ->
                Timber.d("$exception")
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.signedInKey] = signedIn
        }
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                Timber.d("$exception")
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val signedInState = preferences[PreferencesKey.signedInKey] ?: false
                signedInState
            }
    }
}