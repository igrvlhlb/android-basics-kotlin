package com.example.wordsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCES_NAME
)

class SettingsDataStore(context: Context) {
}