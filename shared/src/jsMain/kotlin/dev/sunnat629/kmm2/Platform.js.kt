package dev.sunnat629.kmm2

import Utils.timestampToHumanReadable
import kotlinx.browser.document

fun main() {
    TimelineFetcher.startFetchingTimeline { nwTimeline ->
        val timeline = nwTimeline?.time?.let { timestampToHumanReadable(it) }?: throw Exception("NO RESULT")

        updateWebPageWithNewContent("$timeline -- $nwTimeline")
    }


}

fun updateWebPageWithNewContent(content: String) {
    // Implement the logic to update your web page with the new content.
    // This could involve manipulating the DOM to display the new timeline content.
    println("New timeline content: $content")
    // For example, if you have a <div id="timeline"> in your HTML:
    val timelineElement = document.getElementById("timeline")
    timelineElement?.textContent = content
}

class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()

@OptIn(ExperimentalJsExport::class)
@JsExport
object Something {

    fun somethingString(): String {
        return "NW TIMELINE"
    }
}