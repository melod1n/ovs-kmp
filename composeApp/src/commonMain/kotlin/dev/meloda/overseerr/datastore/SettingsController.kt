package dev.meloda.overseerr.datastore

import dev.meloda.overseerr.datastore.model.AppSettings
import dev.meloda.overseerr.datastore.model.ThemeMode
import dev.meloda.overseerr.ext.setValue
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SettingsController(
    private val store: KStore<AppSettings>,
) {
    private val _settings = MutableStateFlow(AppSettings.EMPTY)
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    suspend fun saveAppSettings() {
        store.set(settings.value)
    }

    suspend fun updateAppSettings(update: (AppSettings) -> AppSettings) {
        store.set(update(settings.value))
    }

    suspend fun loadAppSettings(): AppSettings {
        val loadedSettings = store.get() ?: AppSettings.EMPTY
        _settings.setValue { loadedSettings }
        return loadedSettings
    }

    fun updateThemeMode(newThemeMode: ThemeMode) {
        _settings.setValue { old -> old.copy(themeMode = newThemeMode) }
    }
}
