package dev.meloda.overseerr.screens.url.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.meloda.overseerr.screens.login.presentation.LoginScreen
import dev.meloda.overseerr.screens.url.UrlViewModel

class UrlScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: UrlViewModel = viewModel { UrlViewModel() }

        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
            ) {
                Text(text = "Input url screen")
                Button(
                    onClick = {
                        navigator.push(LoginScreen())
                    }
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}
