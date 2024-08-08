package dev.meloda.overseerr.screens.login.di

import dev.meloda.overseerr.screens.login.LoginViewModelImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModelImpl)
}
