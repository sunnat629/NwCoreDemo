@file:OptIn(ExperimentalJsExport::class)
package dev.sunnat629.nwCoreDemo

@JsName("main")
fun main() {
        updateUI()
}

@JsExport
class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/JS"
}

@JsExport
actual fun getPlatform(): Platform = JsPlatform()
