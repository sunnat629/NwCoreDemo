package dev.sunnat629.kmm2.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpManifestVideoTrack(
    @SerialName("id")
    val id: String? = null,
    @SerialName("entityId")
    val entityId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("localizedNames")
    val localizedNames: List<LocalizedString>? = null,
    @SerialName("unsynced")
    val unsynced: Boolean? = null,
    @SerialName("detached")
    val detached: Boolean? = null,
    @SerialName("videos")
    val videos: List<ExpManifestAVObject>? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
) : ExpManifestTrack() {
    override val type: TrackType = TrackType.VIDEO
}

@Serializable
data class VideoLink(
    @SerialName("id")
    val id: String? = null,
    @SerialName("trackId")
    val trackId: String? = null,
    @SerialName("unavailabilities")
    val unavailabilities: List<ExpManifestUnavailability>? = null,
)

@Serializable
data class VideoContent(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("videoLinks")
    val videoLinks: List<VideoLink>? = null,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
)

@Serializable
data class Thumbnail(
    @SerialName("id")
    val id: String? = null,
    @SerialName("variants")
    val variants: List<Variant>? = null,
)

@Serializable
data class Variant(
    @SerialName("id")
    val id: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("width")
    val width: Double? = null,
    @SerialName("height")
    val height: Double? = null,
)