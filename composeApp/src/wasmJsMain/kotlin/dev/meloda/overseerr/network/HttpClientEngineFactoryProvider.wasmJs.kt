package dev.meloda.overseerr.network

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual class HttpClientEngineFactoryProvider actual constructor() {
    actual fun get(): HttpClientEngineFactory<*> = Js
}
