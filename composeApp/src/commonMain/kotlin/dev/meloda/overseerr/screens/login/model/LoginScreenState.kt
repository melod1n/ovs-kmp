package dev.meloda.overseerr.screens.login.model

data class LoginScreenState(
    val login: String,
    val password: String,
    val isPasswordVisible: Boolean,
    val isLoginEmptyError: Boolean,
    val isPasswordEmptyError: Boolean
) {
    companion object {
        val EMPTY: LoginScreenState = LoginScreenState(
            login = "",
            password = "",
            isPasswordVisible = false,
            isLoginEmptyError = false,
            isPasswordEmptyError = false
        )
    }
}
