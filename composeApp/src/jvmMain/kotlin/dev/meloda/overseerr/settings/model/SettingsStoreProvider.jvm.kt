package dev.meloda.overseerr.settings.model

import dev.meloda.overseerr.appDir
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath

actual class SettingsStoreProvider actual constructor() {
    actual fun provideStore(): KStore<AppSettings> {
        return storeOf(file = "$appDir/app_settings.json".toPath())
    }
}
