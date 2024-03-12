import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object Utils {

    fun timestampToHumanReadable(timestamp: Long?): String {
        if (timestamp == null) return "Timestamp is null"
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
}