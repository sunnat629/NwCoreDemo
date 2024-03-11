package dev.sunnat629.nwCoreDemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform