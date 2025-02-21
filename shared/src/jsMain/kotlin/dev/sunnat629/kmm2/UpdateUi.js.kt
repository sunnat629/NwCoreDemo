package dev.sunnat629.kmm2

import Utils.timestampToHumanReadable
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.mediacapture.MediaStreamConstraints

internal fun updateUI() {

    val constraints = MediaStreamConstraints(audio = true)

    window.navigator.mediaDevices.getUserMedia(constraints)?.then { stream ->
        updateWebPageWithNewContent("permission", "Microphone access granted")
        // You can now use the stream for your purposes
    }?.catch { error ->
        updateWebPageWithNewContent("permission", "Microphone access denied.")
    }

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
    println("New content: $content")
    // Implement the logic to update your web page with the new content.
    // This could involve manipulating the DOM to display the new timeline content.
    // For example, if you have a <div id="timeline"> in your HTML:
    val element = document.getElementById(elementId)
    element?.textContent = content
}