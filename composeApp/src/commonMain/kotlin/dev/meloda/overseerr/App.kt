package dev.meloda.overseerr

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.meloda.overseerr.datastore.SettingsController
import dev.meloda.overseerr.screens.main.MainScreen
import dev.meloda.overseerr.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

var appDir: String = ""

@Composable
internal fun App() {
    LaunchedEffect(true) {
        Napier.base(DebugAntilog())
    }

    val settingsController: SettingsController = koinInject()
    val settings by settingsController.settings.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        settingsController.loadAppSettings()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppTheme(themeMode = settings.themeMode) {
            Surface(
                modifier = Modifier
                    .width(360.dp)
                    .height(640.dp)
            ) {
                MainScreen()
            }
        }
    }
}
