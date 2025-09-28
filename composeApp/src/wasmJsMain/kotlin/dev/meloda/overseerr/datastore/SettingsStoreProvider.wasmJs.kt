package dev.meloda.overseerr.datastore

import dev.meloda.overseerr.datastore.model.AppSettings
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf

actual class SettingsStoreProvider actual constructor() {
    actual fun provideStore(): KStore<AppSettings> = storeOf(key = "app_settings")
}
