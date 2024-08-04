package dev.meloda.overseerr.di

import dev.meloda.overseerr.model.Platform
import dev.meloda.overseerr.network.di.networkModule
import dev.meloda.overseerr.screens.login.di.loginModule
import dev.meloda.overseerr.screens.url.di.urlModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::Platform)

    includes(
        networkModule,
        loginModule,
        urlModule
    )
}
