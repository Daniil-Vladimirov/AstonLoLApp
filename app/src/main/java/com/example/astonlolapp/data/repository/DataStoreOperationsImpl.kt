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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStorePref: DataStore<Preferences> by preferencesDataStore(name = HERO_PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperationsAbs {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(HERO_PREFERENCES_NAME)
    }

    private val dataStore = context.dataStorePref


    override suspend fun insertPreferences(data: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingKey] = data
        }
    }

    override fun readPreferences(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingKey] ?: false
                onBoardingState

            }
    }
}