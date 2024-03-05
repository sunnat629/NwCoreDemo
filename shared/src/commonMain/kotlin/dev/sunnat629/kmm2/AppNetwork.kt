import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AppNetwork {

    val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend inline fun <reified T> fetchFromNetwork(
        url: String
    ): Result<T> = withContext(Dispatchers.Default) {
        try {
            val httpResponse: HttpResponse = httpClient.get(url) {
                // Optional: Configure the request further if needed
                // e.g., headers, timeout, etc.
            }

            if (httpResponse.status == HttpStatusCode.OK) {
                // Use `body` to deserialize directly into the reified type T
                Result.success(httpResponse.body())
            } else {
                // Handle non-successful status codes appropriately
                Result.failure(RuntimeException("Received non-OK response: ${httpResponse.status}"))
            }
        } catch (e: Exception) {
            // Handle exceptions such as network errors, serialization issues, etc.
            Result.failure(e)
        }
    }
}