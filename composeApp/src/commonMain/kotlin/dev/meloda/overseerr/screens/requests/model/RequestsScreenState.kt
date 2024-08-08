package dev.meloda.overseerr.screens.requests.model

import dev.meloda.overseerr.screens.requests.ApiInfo

data class RequestsScreenState(
    val dummyItems: List<Int>,
    val isLoading: Boolean,
    val apiInfo: ApiInfo?,
    val apiErrorText: String?
) {
    companion object {
        val EMPTY: RequestsScreenState = RequestsScreenState(
            dummyItems = List(50) { it },
            isLoading = false,
            apiInfo = null,
            apiErrorText = null
        )
    }
}
