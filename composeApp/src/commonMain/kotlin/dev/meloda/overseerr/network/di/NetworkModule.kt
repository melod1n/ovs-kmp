package dev.meloda.overseerr.network.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(CIO) {

        }
    }
}
