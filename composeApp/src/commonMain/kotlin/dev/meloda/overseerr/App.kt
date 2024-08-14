package dev.meloda.overseerr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import dev.meloda.overseerr.screens.main.MainScreen
import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

var appDir: String = ""

@Composable
internal fun App() = KoinContext {
    LaunchedEffect(true) {
        Napier.base(DebugAntilog())
    }

    val settingsController: SettingsController = koinInject()
    val settings by settingsController.settings.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        settingsController.loadAppSettings()
    }

    AppTheme(themeMode = settings.themeMode) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator(MainScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
