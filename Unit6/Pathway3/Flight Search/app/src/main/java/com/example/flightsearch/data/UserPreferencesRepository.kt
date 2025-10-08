package com.example.flightsearch.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.flightsearch.data.UserPreferencesKeys.SEARCH_VALUE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class UserPreferences(
    val searchValue: String = "",
)

object UserPreferencesKeys {
    val SEARCH_VALUE = stringPreferencesKey("search_value")
}

class UserPreferencesRepository (
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun updateUserPreferences(
        searchValue: String,
    ) {
        dataStore.edit { preferences ->
            preferences[SEARCH_VALUE] = searchValue
        }
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                // Handle the exception
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                searchValue = preferences[SEARCH_VALUE] ?: "",
            )
        }
}