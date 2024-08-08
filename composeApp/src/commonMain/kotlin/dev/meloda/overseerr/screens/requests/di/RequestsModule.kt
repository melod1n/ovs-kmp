package dev.meloda.overseerr.screens.requests.di

import dev.meloda.overseerr.screens.requests.RequestsViewModelImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val requestsModule = module {
    viewModelOf(::RequestsViewModelImpl)
}
