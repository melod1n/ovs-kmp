package dev.meloda.overseerr.network

import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpClientEngineFactoryProvider() {
    fun get(): HttpClientEngineFactory<*>
}
