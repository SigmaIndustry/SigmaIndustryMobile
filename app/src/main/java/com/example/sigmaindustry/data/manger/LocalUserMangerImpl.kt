package com.example.sigmaindustry.data.manger

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sigmaindustry.domain.manger.LocalUserManger
import com.example.sigmaindustry.util.Constants
import com.example.sigmaindustry.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserMangerImpl @Inject constructor(
    private val application: Application
) : LocalUserManger {

    override suspend fun saveAppEntry() {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override suspend fun saveToken(token: String) {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.TOKEN] = token
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun readToken(): String? {

        return application.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.TOKEN] ?: null
        }.first()

    }

}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    var TOKEN = stringPreferencesKey(Constants.TOKEN)
}