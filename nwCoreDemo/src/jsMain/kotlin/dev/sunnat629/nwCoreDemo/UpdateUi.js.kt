package dev.sunnat629.nwCoreDemo

import kotlinx.browser.document
import Utils.timestampToHumanReadable
import org.w3c.dom.HTMLInputElement

internal fun updateUI() {
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

    val manifestInput = document.getElementById("manifestInput") as? HTMLInputElement

    document.getElementById("button3")?.addEventListener("click", {
        manifestInput?.value?.let { manifestId ->
            if (manifestId.isNotEmpty()) {
                ExpManifestFetcher.fetchedManifest(manifestId) { expManifest ->
                    val content = "EXP Name: ${expManifest?.name ?: "Invalid expId"}"
                    updateWebPageWithNewContent("manifestDisplay", content)
                }
            } else {
                // Optionally handle empty input case
                updateWebPageWithNewContent("manifestDisplay", "Please enter a manifest ID.")
            }
        }
    })
    
    npmLibCheck()
}

private fun npmLibCheck() {
    updateWebPageWithNewContent("npmLib1", "arrayOf(1, 2, 3, 4, 5) is sorted: ${sorted(arrayOf(1, 2, 3, 4, 5))}")
    updateWebPageWithNewContent("npmLib2", "arrayOf(1, 23, 4, 5) is sorted: ${sorted(arrayOf(1, 23, 4, 5))}")
}

fun updateWebPageWithNewContent(elementId: String, content: String) {
    // Implement the logic to update your web page with the new content.
    // This could involve manipulating the DOM to display the new timeline content.
    println("New timeline content: $content")
    // For example, if you have a <div id="timeline"> in your HTML:
    val timelineElement = document.getElementById(elementId)
    timelineElement?.textContent = content
}