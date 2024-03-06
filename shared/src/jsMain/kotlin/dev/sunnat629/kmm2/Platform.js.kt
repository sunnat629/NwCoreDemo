package dev.sunnat629.kmm2

import Utils.timestampToHumanReadable
import kotlinx.browser.document

fun main() {
        document.getElementById("button1")?.addEventListener("click", {
            // Example of asynchronous operation, you might want to fetch data from an API
            TimelineFetcher.startFetchingTimeline { nwTimeline ->
                val timeline = nwTimeline?.time?.let { timestampToHumanReadable(it) } ?: "NO RESULT"
                updateWebPageWithNewContent("timeDisplay", "Nw Timeline: $timeline")
            }
        })

    document.getElementById("button2")?.addEventListener("click", {
        // Simulate another fetching operation, you can adjust based on your logic
        TimelineFetcher.stopFetchingTimeline()
        updateWebPageWithNewContent("timeDisplay", "Stopped Fetching")
    })

    document.getElementById("button3")?.addEventListener("click", {
        // Simulate another fetching operation, you can adjust based on your logic
        ExpManifestFetcher.fetchedManifest("p78x44cv1rrm23lx") {
            updateWebPageWithNewContent("manifestDisplay","EXP Name: ${it?.name}")
        }
    })
}

fun updateWebPageWithNewContent(elementId: String, content: String) {
    // Implement the logic to update your web page with the new content.
    // This could involve manipulating the DOM to display the new timeline content.
    println("New timeline content: $content")
    // For example, if you have a <div id="timeline"> in your HTML:
    val timelineElement = document.getElementById(elementId)
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