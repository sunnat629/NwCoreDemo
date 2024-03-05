package dev.sunnat629.kmm2.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpManifestData(
    @SerialName("id")
    val id: String? = null,
    @SerialName("formatVersion")
    val formatVersion: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("localizedNames")
    val localizedNames: List<LocalizedString>? = null,
    @SerialName("start")
    val start: Double? = null,
    @SerialName("duration")
    val duration: Double? = null,
    @SerialName("ended")
    val ended: Boolean? = null,
    @SerialName("realTimeReference")
    val realTimeReference: String? = null,
    @SerialName("globalAudios")
    val globalAudios: List<ExpManifestAudioContent>? = null,
    @SerialName("videoTracks")
    val videoTracks: List<ExpManifestVideoTrack>? = null,
    @SerialName("audioTracks")
    val audioTracks: List<ExpManifestAudioTrack>? = null,
    @SerialName("entities")
    val entities: List<ExpManifestEntity>? = null,
    @SerialName("clips")
    val clips: List<ExpManifestClip>? = null,
    @SerialName("labels")
    val labels: List<ExpManifestLabel>? = null,
    @SerialName("sections")
    val sections: List<ExpManifestSection>? = null,
    @SerialName("moments")
    val moments: List<ExpManifestMoment>? = null,
    @SerialName("extensions")
    val extensions: ExpManifestExtensions? = null,
    @SerialName("videoQuality")
    val videoQuality: ExpManifestVideoQuality? = null,
)

@Serializable
data class ExpManifestVideoQuality (
    @SerialName("levels")
    val levels: List<ExpManifestVideoQualityLevel>? = null,
    @SerialName("minLevelId")
    val minLevelId: String? = null
    ) {

    @Serializable
    data class ExpManifestVideoQualityLevel(
        @SerialName("id")
        val id: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("pixelCount")
        val pixelCount: Int? = null,
        @SerialName("tags")
        val tags: List<String>? = null,

    )
}


@Serializable
data class LocalizedString (
    @SerialName("locales")
    val locales: List<String>? = null,
    @SerialName("value")
    val value: String? = null,
)

@Serializable
data class ExpManifestLabel(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
)

@Serializable
data class ExpManifestClip(
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
)

@Serializable
data class ExpManifestSection(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("start")
    val start: Double? = null,
    @SerialName("end")
    val end: Double? = null,
    @SerialName("extensions")
    val extensions: ExpManifestExtensions? = null,
)

@Serializable
data class ExpManifestMoment(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("start")
    val start: Double? = null,
    @SerialName("end")
    val end: Double? = null,
    @SerialName("extensions")
    val extensions: ExpManifestExtensions? = null,
)

@Serializable
data class CurrentPlaybackDetails(
    val payload: String? = null,
    val selectedVideoEntityId: String? = null,
    val selectedGlobalAudioTrackId: String? = null,
    val expManifest: ExpManifestData? = null,
)