package dev.meloda.overseerr.datastore

import dev.meloda.overseerr.appDir
import dev.meloda.overseerr.datastore.model.AppSettings
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.io.files.Path

actual class SettingsStoreProvider actual constructor() {
    actual fun provideStore(): KStore<AppSettings> {
        return storeOf(file = Path("${appDir}/app_settings.json"))
    }
}
