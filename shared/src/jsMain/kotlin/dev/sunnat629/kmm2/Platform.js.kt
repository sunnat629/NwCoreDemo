package dev.sunnat629.kmm2

import Utils.timestampToHumanReadable
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement

fun main() {
//    console.log(sorted(arrayOf(1,2,3)))
        updateUI()
}

class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()
