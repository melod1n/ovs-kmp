package dev.meloda.overseerr.network.model

import io.ktor.client.engine.*

expect class HttpClientEngineFactoryProvider() {
    fun get(): HttpClientEngineFactory<*>
}
