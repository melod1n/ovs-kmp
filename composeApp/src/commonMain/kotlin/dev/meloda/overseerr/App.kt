package dev.meloda.overseerr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import dev.meloda.overseerr.screens.url.presentation.UrlScreen
import dev.meloda.overseerr.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
internal fun App() = KoinContext {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator(UrlScreen())
        }
    }
}
