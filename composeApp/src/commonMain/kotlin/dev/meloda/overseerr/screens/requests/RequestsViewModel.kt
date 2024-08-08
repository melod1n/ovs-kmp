package dev.meloda.overseerr.screens.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.screens.requests.model.RequestsScreenState
import dev.meloda.overseerr.settings.SettingsController
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.seconds

interface RequestsViewModel {
    val screenState: StateFlow<RequestsScreenState>

    fun onRefresh()

    fun onSuccessMessageShown()
    fun onErrorMessageShown()
}

class RequestsViewModelImpl(
    private val httpClient: HttpClient,
    private val settingsController: SettingsController
) : ViewModel(), RequestsViewModel {

    override val screenState = MutableStateFlow(RequestsScreenState.EMPTY)

    override fun onRefresh() {
        viewModelScope.launch {
            screenState.setValue { old -> old.copy(isLoading = true) }
            delay(1.seconds)
            loadInfo()
            screenState.setValue { old -> old.copy(isLoading = false) }
        }
    }

    override fun onSuccessMessageShown() {
        screenState.setValue { old -> old.copy(apiInfo = null) }
    }

    override fun onErrorMessageShown() {
        screenState.setValue { old -> old.copy(apiErrorText = null) }
    }

    private fun loadInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                httpClient.get("${settingsController.settings.value.url}/api/v1") {
                    headers {
                        append("X-Api-Key", settingsController.settings.value.plexToken)
                    }
                }.body() as ApiInfo
            }.fold(
                onSuccess = { response ->
                    Napier.d { "Response: $response" }
                    screenState.setValue { old -> old.copy(apiInfo = response) }
                },
                onFailure = { error ->
                    Napier.e(error) { "Error occurred" }
                    screenState.setValue { old -> old.copy(apiErrorText = error.message.toString()) }
                }
            )
        }
    }
}

@Serializable
data class ApiInfo(
    val api: String,
    val version: String
)
