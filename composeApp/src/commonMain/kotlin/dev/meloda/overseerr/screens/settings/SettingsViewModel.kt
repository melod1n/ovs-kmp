package dev.meloda.overseerr.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.meloda.overseerr.datastore.SettingsController
import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.screens.settings.model.SettingsScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(private val settingsController: SettingsController) : ViewModel() {

    private val _screenState: MutableStateFlow<SettingsScreenState> = MutableStateFlow(SettingsScreenState.EMPTY)
    val screenState: StateFlow<SettingsScreenState> = _screenState.asStateFlow()

    init {
        settingsController.settings
            .onEach { settings ->
                _screenState.emit(
                    screenState.value.copy(
                        url = settings.url.orEmpty(),
                        plexToken = settings.plexToken.orEmpty()
                    )
                )
            }
            .launchIn(viewModelScope)
    }

    fun onScreenOpened() {
        _screenState.setValue { old ->
            old.copy(
                url = settingsController.settings.value.url.orEmpty(),
                plexToken = settingsController.settings.value.plexToken.orEmpty()
            )
        }
    }

    fun onUrlInputChanged(newText: String) {
        _screenState.setValue { old -> old.copy(url = newText) }
    }

    fun onPlexTokenInputChanged(newToken: String) {
        _screenState.setValue { old -> old.copy(plexToken = newToken) }
    }

    fun onLoadButtonClicked() {
        viewModelScope.launch {
            val settings = settingsController.loadAppSettings()
            withContext(Dispatchers.Main) {
                _screenState.emit(
                    screenState.value.copy(
                        url = settings.url.orEmpty(),
                        plexToken = settings.plexToken.orEmpty()
                    )
                )
            }
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch {
            settingsController.updateAppSettings { settings ->
                settings.copy(
                    url = screenState.value.url.trim().ifEmpty { null },
                    plexToken = screenState.value.plexToken.trim().ifEmpty { null }
                )
            }
        }
    }
}
