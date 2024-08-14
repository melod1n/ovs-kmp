package dev.meloda.overseerr.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val url: String = "",
    val plexToken: String = "",
    val themeMode: ThemeMode = ThemeMode.System
) {
    companion object {
        val EMPTY: AppSettings = AppSettings()
    }
}
