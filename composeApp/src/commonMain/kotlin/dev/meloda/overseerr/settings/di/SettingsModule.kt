package dev.meloda.overseerr.settings.di

import dev.meloda.overseerr.appDir
import dev.meloda.overseerr.settings.SettingsController
import dev.meloda.overseerr.settings.SettingsControllerImpl
import dev.meloda.overseerr.settings.model.AppSettings
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    single {
        storeOf<AppSettings>(file = "$appDir/app_settings.json".toPath())
    }
    singleOf(::SettingsControllerImpl) bind SettingsController::class
}
