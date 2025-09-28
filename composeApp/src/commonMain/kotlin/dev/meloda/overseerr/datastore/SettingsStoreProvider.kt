package dev.meloda.overseerr.datastore

import dev.meloda.overseerr.datastore.model.AppSettings
import io.github.xxfast.kstore.KStore

expect class SettingsStoreProvider() {
    fun provideStore(): KStore<AppSettings>
}
