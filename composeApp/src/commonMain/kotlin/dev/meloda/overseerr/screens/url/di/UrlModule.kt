package dev.meloda.overseerr.screens.url.di

import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.meloda.overseerr.screens.url.UrlViewModel
import org.koin.dsl.module

val urlModule = module {
    viewModelFactory {
        UrlViewModel()
    }
}
