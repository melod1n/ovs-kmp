package dev.meloda.overseerr.settings.di

import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.settings.SettingsControllerImpl
import dev.meloda.overseerr.settings.model.SettingsStoreProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsStoreProvider().provideStore() }
    singleOf(::SettingsControllerImpl) bind SettingsController::class
}
