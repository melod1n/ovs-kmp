package dev.meloda.overseerr.model

internal actual class Platform actual constructor() {
    actual val name: String
        get() = "JVM"
}
