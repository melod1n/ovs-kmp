package dev.meloda.overseerr.screens.url.model

data class UrlScreenState(
    val url: String,
    val plexToken: String,
    val isWrongUrlError: Boolean
) {
    companion object {
        val EMPTY: UrlScreenState = UrlScreenState(
            url = "",
            plexToken = "",
            isWrongUrlError = false
        )
    }
}
