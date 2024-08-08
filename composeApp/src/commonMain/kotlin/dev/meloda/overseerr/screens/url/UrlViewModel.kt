package dev.meloda.overseerr.screens.url

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.screens.url.model.UrlScreenState
import dev.meloda.overseerr.settings.SettingsController
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

interface UrlViewModel {
    val screenState: StateFlow<UrlScreenState>

    fun onUrlInputChanged(newText: String)
    fun onPlexTokenInputChanged(newToken: String)
    fun onLoadButtonClicked()
    fun onSaveButtonClicked()
    fun onTestButtonClicked()
}

class UrlViewModelImpl(
    private val settingsController: SettingsController
) : ViewModel(), UrlViewModel {

    override val screenState = MutableStateFlow(UrlScreenState.EMPTY)

    init {
        settingsController.settings
            .onEach { settings ->
                screenState.setValue { old ->
                    old.copy(
                        url = settings.url,
                        plexToken = settings.plexToken
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onUrlInputChanged(newText: String) {
        screenState.setValue { old -> old.copy(url = newText) }
    }

    override fun onPlexTokenInputChanged(newToken: String) {
        screenState.setValue { old -> old.copy(plexToken = newToken) }
    }

    override fun onLoadButtonClicked() {
        viewModelScope.launch {
            val settings = settingsController.loadAppSettings()

            screenState.setValue { old ->
                old.copy(
                    url = settings.url,
                    plexToken = settings.plexToken
                )
            }
        }
    }

    override fun onSaveButtonClicked() {
        viewModelScope.launch {
            settingsController.updateAppSettings { settings ->
                settings.copy(
                    url = screenState.value.url,
                    plexToken = screenState.value.plexToken
                )
            }
        }
    }

    override fun onTestButtonClicked() {
        Napier.v("Test button clicked")
    }
}
