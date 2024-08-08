package dev.meloda.overseerr.network.model

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual class HttpClientEngineFactoryProvider actual constructor() {
    actual fun get(): HttpClientEngineFactory<*> = Darwin
}
