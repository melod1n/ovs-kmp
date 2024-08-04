package dev.meloda.overseerr.screens.login.di

import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.meloda.overseerr.screens.login.LoginViewModel
import org.koin.dsl.module

val loginModule = module {
    viewModelFactory(::LoginViewModel)
}
