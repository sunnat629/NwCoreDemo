package dev.sunnat629.nwCoreDemo.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ExpManifestGlobalAudioTrack(
    @SerialName("id")
    val id: String? = null,
    @SerialName("globalTrackId")
    val globalAudioTrackId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("unsynced")
    val unsynced: Boolean? = null,
    @SerialName("audios")
    val audioObject: List<ExpManifestAVObject>? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
): ExpManifestTrack() {
    override val type: TrackType = TrackType.GLOBAL_AUDIO
}

@Serializable
data class ExpManifestAudioTrack(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("unsynced")
    val unsynced: Boolean? = null,
    @SerialName("audios")
    val audios: List<ExpManifestAVObject>? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
): ExpManifestTrack() {
    override val type: TrackType = TrackType.AUDIO
}

@Serializable
data class ExpManifestAudioContent(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("audioLinks")
    val audioLinks: List<AudioLink>? = null,
    @SerialName("languageCodes")
    val languageCodes: List<String>? = null,
)

@Serializable
data class AudioLink(
    @SerialName("id")
    val id: String? = null,
    @SerialName("trackId")
    val trackId: String? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
)

@Serializable
data class AudioContent(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("audioLinks")
    val audioLinks: List<AudioLink>? = null,
    @SerialName("languageCodes")
    val languageCodes: List<String>? = null,
)