package dev.sunnat629.kmm2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform