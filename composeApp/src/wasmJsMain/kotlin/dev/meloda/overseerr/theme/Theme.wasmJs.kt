package dev.meloda.overseerr.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavController
import androidx.navigation.bindToNavigation
import kotlinx.browser.window

@Composable
internal actual fun SystemAppearance(isDark: Boolean) {
}

@OptIn(ExperimentalBrowserHistoryApi::class)
@Composable
internal actual fun NavigationSettings(navController: NavController) {
    LaunchedEffect(navController) {
        window.bindToNavigation(navController)
    }
}