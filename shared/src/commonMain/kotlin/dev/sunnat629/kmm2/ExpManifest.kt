package dev.sunnat629.kmm2

import AppNetwork
import dev.sunnat629.kmm2.models.ExpManifestData
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
object ExpManifestFetcher {
  
    private val scope = CoroutineScope(Dispatchers.Default)
    
    private val _expManifest = MutableSharedFlow<ExpManifestData>()
    val expManifest = _expManifest.asSharedFlow()

    private suspend fun fetchNwTimeline(url: String): ExpManifestData? {
        return AppNetwork.httpClient.get(url).body<ExpManifestData?>()
    }
    
    fun fetchedManifest(manifestId: String, onUpdate: (ExpManifestData?) -> Unit) {
        val callback = object : ExpManifestCallback {
            override fun onUpdate(timeline: ExpManifestData?) {
                onUpdate(timeline)
            }
        }
        startFetching(manifestId, callback)
    }

    private fun startFetching(manifestId: String, callback: ExpManifestCallback) {
        scope.launch {
            val url = "https://showcase-content.cdn.test.nativewaves.com/programs/v2/$manifestId"
            println("url: $url")
            try {
                    val result = fetchNwTimeline(url) // Simulate fetching timeline
                    println(result?.toString())
                    result?.let { _expManifest.emit(it) } // Emit the timeline to the SharedFlow
                    callback.onUpdate(result) // Notify Swift through the callback
                } catch (e: Throwable) {
                    println("Error fetching timeline: ${e.message}")
                }
            }
    }
    
}

interface ExpManifestCallback {
    fun onUpdate(timeline: ExpManifestData?)
}