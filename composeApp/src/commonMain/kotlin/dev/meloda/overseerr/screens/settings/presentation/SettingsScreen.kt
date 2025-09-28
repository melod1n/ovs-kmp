package dev.meloda.overseerr.screens.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.meloda.overseerr.datastore.SettingsController
import dev.meloda.overseerr.datastore.model.ThemeMode
import dev.meloda.overseerr.screens.settings.SettingsViewModel
import dev.meloda.overseerr.theme.LocalPadding
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = koinViewModel()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    val settingsController: SettingsController = koinInject()
    val settings by settingsController.settings.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.onScreenOpened()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPadding.current)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var localUrl by rememberSaveable(screenState.url) {
            mutableStateOf(screenState.url)
        }
        var localPlexToken by rememberSaveable(screenState.plexToken) {
            mutableStateOf(screenState.plexToken)
        }

        Text(
            text = "Dark mode",
            style = MaterialTheme.typography.titleMedium
        )

        SingleChoiceSegmentedButtonRow {
            SegmentedButton(
                selected = settings.themeMode == ThemeMode.Light,
                onClick = { settingsController.updateThemeMode(ThemeMode.Light) },
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    bottomStart = 4.dp
                ),
                label = { Text(text = "Light") }
            )

            SegmentedButton(
                selected = settings.themeMode == ThemeMode.Dark,
                onClick = { settingsController.updateThemeMode(ThemeMode.Dark) },
                shape = RoundedCornerShape(0.dp),
                label = { Text(text = "Dark") }
            )

            SegmentedButton(
                selected = settings.themeMode == ThemeMode.System,
                onClick = { settingsController.updateThemeMode(ThemeMode.System) },
                shape = RoundedCornerShape(
                    topEnd = 4.dp,
                    bottomEnd = 4.dp
                ),
                label = { Text(text = "System") }
            )
        }

        Text(
            text = "API",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = localUrl,
            onValueChange = { text ->
                localUrl = text
                viewModel.onUrlInputChanged(text)
            },
            placeholder = { Text(text = "Url") },
            isError = screenState.isWrongUrlError,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Uri
            )
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = localPlexToken,
            onValueChange = { text ->
                localPlexToken = text
                viewModel.onPlexTokenInputChanged(text)
            },
            placeholder = { Text(text = "Token") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Go)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = viewModel::onSaveButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Save")
            }

            Button(
                onClick = viewModel::onLoadButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Load")
            }
        }
    }
}
