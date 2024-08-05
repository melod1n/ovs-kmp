package dev.meloda.overseerr.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val url: String
) {
    companion object {
        val EMPTY: AppSettings = AppSettings(
            url = ""
        )
    }
}
