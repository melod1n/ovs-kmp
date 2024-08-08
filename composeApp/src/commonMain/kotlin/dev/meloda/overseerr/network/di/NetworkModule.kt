package dev.meloda.overseerr.network.di

import dev.meloda.overseerr.network.model.HttpClientEngineFactoryProvider
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::HttpClientEngineFactoryProvider)
    single {
        HttpClient(engineFactory = get<HttpClientEngineFactoryProvider>().get()) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}
