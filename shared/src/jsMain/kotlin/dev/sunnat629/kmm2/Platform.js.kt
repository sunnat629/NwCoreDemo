package dev.sunnat629.kmm2

fun main() {
//    console.log(sorted(arrayOf(1,2,3)))
    updateUI()
}

//p78x44cv1rrm23lx
class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()
