package dev.meloda.overseerr.settings.model

import io.github.xxfast.kstore.KStore

expect class SettingsStoreProvider() {

    fun provideStore(): KStore<AppSettings>
}
