package com.Stackhouse.android.gratitude
import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





private val GRAT_ENTRIES_KEY = stringPreferencesKey("grat_entries")

suspend fun saveTextList(context: Context, updatedList: List<GratEntries>) {
    val jsonString = Json.encodeToString(updatedList)
    context.dataStore.edit { preferences ->
            preferences[GRAT_ENTRIES_KEY] = jsonString
        }
    }

fun getTextList(context: Context): Flow<List<GratEntries>> {
    return context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[GRAT_ENTRIES_KEY] ?: "[]"
            Json.decodeFromString(jsonString)
        }
}

