package dev.meloda.overseerr.screens.settings.model

data class SettingsScreenState(
    val url: String,
    val plexToken: String,
    val isWrongUrlError: Boolean,
) {
    companion object {
        val EMPTY: SettingsScreenState = SettingsScreenState(
            url = "",
            plexToken = "",
            isWrongUrlError = false
        )
    }
}
