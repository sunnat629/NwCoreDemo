package dev.sunnat629.kmm2

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

@Serializable
data class NwTimeline(
    @SerialName("region")
    val region: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("time")
    val time: Long? = null
)

object TimelineFetcher {

    private var httpClient: HttpClient? = null

    private suspend fun fetchNwTimeline(): NwTimeline? {
        return httpClient?.get("https://time.api.nativewaves.com/")?.body<NwTimeline>()
    }

    private val _timelineFlow = MutableSharedFlow<String>()
    val timelineFlow = _timelineFlow.asSharedFlow()
    private val scope = CoroutineScope(Dispatchers.Default)
    private var fetchJob: Job? = null

    private fun tickerFlow(delayMillis: Long) = flow {
        while (true) {
            emit(Unit) // Emit an item to trigger fetching
            delay(delayMillis) // Wait for the specified delay
        }
    }


    // Assuming this is defined in your Kotlin Multiplatform project
    fun startFetchingTimeline(onUpdate: (String) -> Unit) {
        val callback = object : TimelineUpdateCallback {
            override fun onUpdate(timeline: String) {
                onUpdate(timeline)
            }
        }
        startFetching(callback)
    }

    private fun startFetching(callback: TimelineUpdateCallback) {
        // Cancel any existing job to prevent multiple fetch jobs

        if (httpClient == null) {
            httpClient = HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
        }

        fetchJob?.cancel()
        fetchJob = scope.launch {
            tickerFlow(5000L).collect { _ ->
                try {
                    val result = fetchNwTimeline() // Simulate fetching timeline
                    val timeline = result?.time?.let { timestampToHumanReadable(it) }?: throw Exception("NO RESULT")
                    _timelineFlow.emit(timeline) // Emit the timeline to the SharedFlow
                    callback.onUpdate(timeline) // Notify Swift through the callback
                } catch (e: Throwable) {
                    println("Error fetching timeline: ${e.message}")
                }
            }
        }
    }

    fun stopFetchingTimeline() {
        httpClient?.close()
        httpClient = null
        fetchJob?.cancel() // Cancel the job, which stops the coroutine
        fetchJob = null // Reset the job reference
    }
}

interface TimelineUpdateCallback {
    fun onUpdate(timeline: String)
}

fun timestampToHumanReadable(timestamp: Long): String {
    // Create an Instant from the timestamp (milliseconds)
    val instant = Instant.fromEpochMilliseconds(timestamp)

    // Convert the Instant to LocalDateTime in the system's default time zone
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // Format the LocalDateTime as a String (customize as needed)

    return "${localDateTime.year}-${
        localDateTime.monthNumber.toString().padStart(2, '0')
    }-${localDateTime.dayOfMonth.toString().padStart(2, '0')} ${
        localDateTime.hour.toString().padStart(2, '0')
    }:${localDateTime.minute.toString().padStart(2, '0')}:${
        localDateTime.second.toString().padStart(2, '0')
    }"
}