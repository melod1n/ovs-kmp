package dev.meloda.overseerr.screens.url.model

data class UrlScreenState(
    val url: String,
    val isWrongUrlError: Boolean
) {
    companion object {
        val EMPTY: UrlScreenState = UrlScreenState(
            url = "",
            isWrongUrlError = false
        )
    }
}
