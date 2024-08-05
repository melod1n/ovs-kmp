package dev.meloda.overseerr.network.di

import io.ktor.client.*
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {

        }
    }
}
