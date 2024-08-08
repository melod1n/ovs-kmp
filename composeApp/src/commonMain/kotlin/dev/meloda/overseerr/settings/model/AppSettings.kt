package dev.meloda.overseerr.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val url: String = "",
    val plexToken: String = ""
) {
    companion object {
        val EMPTY: AppSettings = AppSettings()
    }
}
