package dev.sunnat629.nwCoreDemo.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpManifestEntity(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("videoContents")
    val videoContents: List<VideoContent>? = null,
    @SerialName("audioContents")
    val audioContents: List<AudioContent>? = null,
    @SerialName("labelIds")
    val labelIds: List<String>? = null,
    @SerialName("extensions")
    val extensions: ExpManifestExtensions? = null,
)

enum class TrackType { AUDIO, VIDEO, GLOBAL_AUDIO,/*VIDEO360,*/ UNKNOWN }

@Serializable
abstract class ExpManifestTrack {
    abstract val type: TrackType
}

@Serializable
data class ExpManifestAVObject(
    @SerialName("id")
    val id: String? = null,
    @SerialName("localizedNames")
    val localizedNames: List<LocalizedString>? = null,
    @SerialName("start")
    val start: Double? = null,
    @SerialName("duration")
    val duration: Double? = null,
    @SerialName("ended")
    val ended: Boolean? = null,
    @SerialName("liveOnly")
    val liveOnly: Boolean? = null,
    @SerialName("sources")
    val sources: List<ExpManifestAVSource>? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
)
