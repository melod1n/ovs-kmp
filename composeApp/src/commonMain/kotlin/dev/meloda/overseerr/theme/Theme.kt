package dev.meloda.overseerr.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.navigation.NavController
import dev.chrisbanes.haze.HazeState
import dev.meloda.overseerr.datastore.model.ThemeMode

val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }
val LocalHazeState = compositionLocalOf { HazeState(true) }
val LocalPadding = compositionLocalOf { PaddingValues() }

@Composable
internal fun AppTheme(
    themeMode: ThemeMode = ThemeMode.System,
    content: @Composable () -> Unit,
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember(themeMode, systemIsDark) {
        mutableStateOf(
            if (themeMode == ThemeMode.System) systemIsDark
            else themeMode == ThemeMode.Dark
        )
    }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) darkColorScheme() else lightColorScheme(),
            content = { Surface(content = content) }
        )
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)

@Composable
internal expect fun NavigationSettings(navController: NavController)
