package com.example.my_newsapplication.data.management

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.my_newsapplication.domain.management.LocalUserManagment
import com.example.my_newsapplication.util.Constants.APP_ENTRY
import com.example.my_newsapplication.util.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagmentImpl(
    private val context : Context
):LocalUserManagment {
    override suspend fun storeAppEntry() {
        context.dataStore.edit {usersetting ->
            usersetting[PreferencesKeys.APPLICATION_ENTRY] = true

        }
    }

    override fun retrieveAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {prefernces ->
            prefernces[PreferencesKeys.APPLICATION_ENTRY]?:false

        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

private object PreferencesKeys{
    val APPLICATION_ENTRY = booleanPreferencesKey(name = APP_ENTRY)
}