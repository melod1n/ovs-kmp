package dev.meloda.overseerr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.meloda.overseerr.screens.login.presentation.LoginScreen
import dev.meloda.overseerr.screens.main.MainScreen
import dev.meloda.overseerr.screens.requests.presentation.RequestsScreen
import dev.meloda.overseerr.screens.url.presentation.UrlScreen
import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.theme.AppTheme
import dev.meloda.overseerr.theme.NavigationSettings
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

    val navController = rememberNavController()

    NavigationSettings(navController)

    AppTheme(themeMode = settings.themeMode) {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = MainScreen
            ) {
                composable<MainScreen> {
                    MainScreen(navController)
                }

                composable<LoginScreen> {
                    LoginScreen(onBack = navController::popBackStack)
                }

                composable<RequestsScreen> {
                    RequestsScreen(onBack = navController::popBackStack)
                }

                composable<UrlScreen> {
                    UrlScreen(onBack = navController::popBackStack)
                }
            }
        }
    }
}
