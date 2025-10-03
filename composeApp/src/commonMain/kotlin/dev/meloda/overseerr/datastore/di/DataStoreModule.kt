package dev.meloda.overseerr.datastore.di

import dev.meloda.overseerr.datastore.SettingsController
import dev.meloda.overseerr.datastore.SettingsStoreProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {
    single { SettingsStoreProvider().provideStore() }
    singleOf(::SettingsController)
}
