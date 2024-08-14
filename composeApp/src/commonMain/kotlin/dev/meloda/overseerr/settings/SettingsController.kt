package dev.meloda.overseerr.settings

import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.settings.model.AppSettings
import dev.meloda.overseerr.settings.model.ThemeMode
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SettingsController {
    val settings: StateFlow<AppSettings>

    suspend fun saveAppSettings()
    suspend fun updateAppSettings(update: (AppSettings) -> AppSettings)
    suspend fun loadAppSettings(): AppSettings

    fun updateThemeMode(newThemeMode: ThemeMode)
}

class SettingsControllerImpl(
    private val store: KStore<AppSettings>
) : SettingsController {

    override val settings = MutableStateFlow(AppSettings.EMPTY)

    override suspend fun saveAppSettings() {
        store.set(settings.value)
    }

    override suspend fun updateAppSettings(update: (AppSettings) -> AppSettings) {
        store.set(update(settings.value))
    }

    override suspend fun loadAppSettings(): AppSettings {
        val loadedSettings = store.get() ?: AppSettings.EMPTY
        settings.setValue { loadedSettings }
        return loadedSettings
    }

    override fun updateThemeMode(newThemeMode: ThemeMode) {
        settings.setValue { old -> old.copy(themeMode = newThemeMode) }
    }
}
