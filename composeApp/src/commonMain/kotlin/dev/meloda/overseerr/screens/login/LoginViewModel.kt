package dev.meloda.overseerr.screens.login

import androidx.lifecycle.ViewModel
import dev.meloda.overseerr.ext.setValue
import dev.meloda.overseerr.screens.login.model.LoginScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val screenState: StateFlow<LoginScreenState>

    fun onLoginInputChanged(newLogin: String)
    fun onPasswordInputChanged(newPassword: String)
    fun onAuthorizeButtonClicked()
    fun onPasswordVisibilityButtonClicked()
}

class LoginViewModelImpl : ViewModel(), LoginViewModel {

    override val screenState = MutableStateFlow(LoginScreenState.EMPTY)

    override fun onLoginInputChanged(newLogin: String) {
        screenState.setValue { old ->
            old.copy(login = newLogin)
        }
    }

    override fun onPasswordInputChanged(newPassword: String) {
        screenState.setValue { old ->
            old.copy(password = newPassword)
        }
    }

    override fun onAuthorizeButtonClicked() {
        // TODO: 05/08/2024, Danil Nikolaev: add logger
    }

    override fun onPasswordVisibilityButtonClicked() {
        screenState.setValue { old ->
            old.copy(isPasswordVisible = !old.isPasswordVisible)
        }
    }
}
