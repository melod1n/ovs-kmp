package dev.meloda.overseerr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
