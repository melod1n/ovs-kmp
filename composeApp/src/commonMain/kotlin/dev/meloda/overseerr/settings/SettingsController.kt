package dev.meloda.overseerr.settings

import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.settings.model.AppSettings
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SettingsController {
    val settings: StateFlow<AppSettings>

    suspend fun updateAppSettings(update: (AppSettings) -> AppSettings)
    suspend fun loadAppSettings(): AppSettings
}

class SettingsControllerImpl(
    private val store: KStore<AppSettings>
) : SettingsController {

    override val settings = MutableStateFlow(AppSettings.EMPTY)

    override suspend fun updateAppSettings(update: (AppSettings) -> AppSettings) {
        store.set(update(settings.value))
    }

    override suspend fun loadAppSettings(): AppSettings {
        val loadedSettings = store.get() ?: AppSettings.EMPTY
        settings.setValue { loadedSettings }
        return loadedSettings
    }
}
