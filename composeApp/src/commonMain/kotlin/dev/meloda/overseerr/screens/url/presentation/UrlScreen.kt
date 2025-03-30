package dev.meloda.overseerr.screens.url.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.meloda.overseerr.screens.url.UrlViewModel
import dev.meloda.overseerr.screens.url.UrlViewModelImpl
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object UrlScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrlScreen(onBack: () -> Unit = {}) {
    val viewModel: UrlViewModel = koinViewModel<UrlViewModelImpl>()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Url") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenState.url,
                onValueChange = viewModel::onUrlInputChanged,
                placeholder = { Text(text = "Url") },
                isError = screenState.isWrongUrlError,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Go,
                    keyboardType = KeyboardType.Uri
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenState.plexToken,
                onValueChange = viewModel::onPlexTokenInputChanged,
                placeholder = { Text(text = "Token") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Go
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = viewModel::onLoadButtonClicked,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text(text = "Load")
                }

                Button(
                    onClick = viewModel::onSaveButtonClicked,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text(text = "Save")
                }

                Button(
                    onClick = viewModel::onTestButtonClicked,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text(text = "Test")
                }
            }
        }
    }
}