package dev.meloda.overseerr.settings.model

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf

actual class SettingsStoreProvider actual constructor() {
    actual fun provideStore(): KStore<AppSettings> = storeOf(key = "app_settings")
}
