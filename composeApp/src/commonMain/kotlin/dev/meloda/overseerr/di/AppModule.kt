package dev.meloda.overseerr.di

import dev.meloda.overseerr.datastore.di.dataStoreModule
import dev.meloda.overseerr.network.di.networkModule
import dev.meloda.overseerr.screens.login.di.loginModule
import dev.meloda.overseerr.screens.requests.di.requestsModule
import dev.meloda.overseerr.screens.settings.di.settingsModule
import org.koin.dsl.module

val appModule = module {
    includes(
        dataStoreModule,
        networkModule,
        loginModule,
        settingsModule,
        requestsModule
    )
}
