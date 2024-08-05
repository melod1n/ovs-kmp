package dev.meloda.overseerr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import dev.meloda.overseerr.screens.main.MainScreen
import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

var appDir: String = ""

@Composable
internal fun App() = KoinContext {

    val settingsController: SettingsController = koinInject()

    LaunchedEffect(true) {
        settingsController.loadAppSettings()
    }

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator(MainScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
