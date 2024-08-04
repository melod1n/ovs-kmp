package dev.meloda.overseerr.di

import dev.meloda.overseerr.model.Platform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::Platform)
}
