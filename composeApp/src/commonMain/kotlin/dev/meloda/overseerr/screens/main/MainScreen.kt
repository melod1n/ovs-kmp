package dev.meloda.overseerr.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.meloda.overseerr.screens.login.presentation.LoginScreen
import dev.meloda.overseerr.screens.requests.presentation.RequestsScreen
import dev.meloda.overseerr.screens.url.presentation.UrlScreen
import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.settings.model.ThemeMode
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    val settingsController: SettingsController = koinInject()
    val settings by settingsController.settings.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Main screen") },
                actions = {
                    TextButton(
                        onClick = {
                            val newThemeMode = ThemeMode.entries.getOrElse(
                                ThemeMode.entries.indexOf(settings.themeMode) + 1
                            ) { ThemeMode.System }

                            settingsController.updateThemeMode(newThemeMode)
                            coroutineScope.launch {
                                settingsController.saveAppSettings()
                            }
                        }
                    ) {
                        Text(
                            text = when (settings.themeMode) {
                                ThemeMode.System -> "System"
                                ThemeMode.Dark -> "Dark"
                                ThemeMode.Light -> "Light"
                            }
                        )
                    }
                }
            )
        }
    ) { padding ->
        Row(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = { navController.navigate(UrlScreen) },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = "Url")
            }

            Button(
                onClick = { navController.navigate(LoginScreen) },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = { navController.navigate(RequestsScreen) },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = "Requests")
            }
        }
    }
}