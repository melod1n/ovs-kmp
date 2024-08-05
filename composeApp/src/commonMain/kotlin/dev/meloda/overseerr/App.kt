package dev.meloda.overseerr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import dev.meloda.overseerr.screens.main.MainScreen
import dev.meloda.overseerr.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
internal fun App() = KoinContext {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator(MainScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
