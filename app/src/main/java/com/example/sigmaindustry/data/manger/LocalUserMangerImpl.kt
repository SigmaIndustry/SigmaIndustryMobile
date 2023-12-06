package com.example.sigmaindustry.data.manger

import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
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

    val sharedPref = application.getSharedPreferences("Token", ComponentActivity.MODE_PRIVATE)
    override suspend fun saveAppEntry() {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override suspend fun saveToken(token: String?) {
        if (token != null) {
            application.dataStore.edit { settings ->
                settings[PreferenceKeys.TOKEN] = token
            }
            sharedPref.edit().putString("token", token).apply()
        } else{
            application.dataStore.edit {settings ->
                settings.remove(PreferenceKeys.TOKEN)
            }
            sharedPref.edit().remove("token").apply()
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun readToken(): String? {
        val token = sharedPref.getString("token", null)
        val appToken = application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.TOKEN]
        }.first()
        return token
    }

}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    var TOKEN = stringPreferencesKey(Constants.TOKEN)
}