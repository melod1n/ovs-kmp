package dev.meloda.overseerr.screens.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.meloda.overseerr.screens.login.LoginViewModel
import dev.meloda.overseerr.screens.login.LoginViewModelImpl
import dev.meloda.overseerr.screens.login.model.LoginScreenState
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onBack: () -> Unit = {}) {
    val viewModel: LoginViewModel = koinViewModel<LoginViewModelImpl>()
    val screenState: LoginScreenState by viewModel.screenState.collectAsStateWithLifecycle()

    var loginValue by rememberSaveable {
        mutableStateOf(screenState.login)
    }
    var passwordValue by rememberSaveable {
        mutableStateOf(screenState.password)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Log in")
                },
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
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(0.9f),
                value = loginValue,
                onValueChange = { newText ->
                    loginValue = newText
                    viewModel.onLoginInputChanged(newText)
                },
                placeholder = { Text(text = "Login") },
                isError = screenState.isLoginEmptyError,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(0.9f),
                value = passwordValue,
                onValueChange = { newText ->
                    passwordValue = newText
                    viewModel.onPasswordInputChanged(newText)
                },
                placeholder = { Text(text = "Password") },
                isError = screenState.isPasswordEmptyError,
                trailingIcon = {
                    IconButton(onClick = viewModel::onPasswordVisibilityButtonClicked) {
                        Icon(
                            imageVector = if (screenState.isPasswordVisible) {
                                Icons.Rounded.VisibilityOff
                            } else {
                                Icons.Rounded.Visibility
                            },
                            contentDescription = if (screenState.isPasswordVisible) "Password visible icon"
                            else "Password invisible icon"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Go,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (screenState.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                }
            ) {
                Text(text = "Authorize")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}