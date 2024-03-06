import dev.sunnat629.kmm2.NwTimeline
import dev.sunnat629.kmm2.TimelineFetcher
import dev.sunnat629.kmm2.models.ExpManifestData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object AppNetwork {

    val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend inline fun <reified T> fetchFromNetwork(
        url: String
    ): Result<T> = withContext(Dispatchers.Default) {
        try {
            val result = httpClient.get(url).body<T>()
            Result.success(result)
        } catch (e: Exception) {
            // Handle exceptions such as network errors, serialization issues, etc.
            Result.failure(e)
        }
    }
}