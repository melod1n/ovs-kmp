package dev.meloda.overseerr

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.meloda.overseerr.model.Platform
import dev.meloda.overseerr.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = KoinContext {
    val platform: Platform = koinInject()

    AppTheme {
        var loginValue by rememberSaveable {
            mutableStateOf("")
        }
        var passwordValue by rememberSaveable {
            mutableStateOf("")
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Log in")
                    }
                )

                Text(
                    text = "Current platform: ${platform.name}",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = loginValue,
                    onValueChange = { newText -> loginValue = newText },
                    placeholder = { Text(text = "Login") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = passwordValue,
                    onValueChange = { newText -> passwordValue = newText },
                    placeholder = { Text(text = "Password") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                    }
                ) {
                    Text(text = "Authorize")
                }
            }
        }
    }
}
