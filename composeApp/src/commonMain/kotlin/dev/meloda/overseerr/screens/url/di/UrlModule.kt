package dev.meloda.overseerr.screens.url.di

import dev.meloda.overseerr.screens.url.UrlViewModelImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val urlModule = module {
    viewModelOf(::UrlViewModelImpl)
}
