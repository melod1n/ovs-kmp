package dev.meloda.overseerr.screens.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class LoginScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

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
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = navigator::pop
                        ) {
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
