package dev.meloda.overseerr.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val url: String? = null,
    val plexToken: String? = null,
    val themeMode: ThemeMode = ThemeMode.System,
) {
    companion object {
        val EMPTY: AppSettings = AppSettings()
    }
}
