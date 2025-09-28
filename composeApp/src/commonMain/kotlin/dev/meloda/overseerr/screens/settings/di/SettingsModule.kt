package dev.meloda.overseerr.screens.settings.di

import dev.meloda.overseerr.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}
