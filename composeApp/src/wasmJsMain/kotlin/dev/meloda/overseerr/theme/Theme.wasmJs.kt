package dev.meloda.overseerr.theme

import androidx.compose.runtime.Composable
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavController

@Composable
internal actual fun SystemAppearance(isDark: Boolean) {
}

@OptIn(ExperimentalBrowserHistoryApi::class)
@Composable
internal actual fun NavigationSettings(navController: NavController) {
}
