package dev.meloda.overseerr.network

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual class HttpClientEngineFactoryProvider actual constructor() {
    actual fun get(): HttpClientEngineFactory<*> = OkHttp
}
