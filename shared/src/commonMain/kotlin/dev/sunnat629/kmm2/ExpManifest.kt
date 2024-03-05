package dev.sunnat629.kmm2

import AppNetwork
import dev.sunnat629.kmm2.models.ExpManifestData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
object ExpManifestFetcher {
  
    private val scope = CoroutineScope(Dispatchers.Default)
    
    private val _expManifest = MutableSharedFlow<ExpManifestData>()
    val expManifest = _expManifest.asSharedFlow()
    
    private suspend fun fetchNwTimeline(manifestId: String): ExpManifestData? {
        val result = AppNetwork.fetchFromNetwork<ExpManifestData>("https://showcase-content.cdn.test.nativewaves.com/programs/v2/$manifestId")
        return result.getOrElse { null }
    }
    
    private fun startFetching(callback: TimelineUpdateCallback) {
        // Cancel any existing job to prevent multiple fetch jobs

        scope.launch {
            tickerFlow(5000L).collect { _ ->
                try {
                    val result = fetchNwTimeline() // Simulate fetching timeline
                    result?.let { _timelineFlow.emit(it) } // Emit the timeline to the SharedFlow
                    callback.onUpdate(result) // Notify Swift through the callback
                } catch (e: Throwable) {
                    println("Error fetching timeline: ${e.message}")
                }
            }
        }
    }
    
}

interface ExpManifestCallback {
    fun onUpdate(timeline: NwTimeline?)
}