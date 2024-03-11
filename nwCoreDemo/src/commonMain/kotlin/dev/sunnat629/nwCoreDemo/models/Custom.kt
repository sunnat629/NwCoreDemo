package dev.sunnat629.nwCoreDemo.models



import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.Serializable

@Serializable
data class ExpManifestExtensions(
    @SerialName("custom")
    val custom: JsonObject? = null,
    @SerialName("football")
    val football: ExpManifestExtFootball? = null,
)

@Serializable
data class Custom(
    @SerialName("nwBasicV1")
    val nwBasicV1: NwBasicV1? = null,
    @SerialName("advertisement")
    val advertisement: Advertisement? = null,
    @SerialName("optaFootball")
    val optaFootball: OptaFootball? = null,
    @SerialName("experiencePlayback")
    val experience: Experience? = null,
    @SerialName("drm")
    val drm: DRMModel? = null,
)

@Serializable
data class NwBasicV1(
    @SerialName("type")
    val type: String? = null,
    @SerialName("shortName")
    val shortName: String? = null,
    @SerialName("name")
    val smdsEndpoint: String? = null,
    @SerialName("smdsEndpoint")
    val thumbnail: String? = null,
    @SerialName("syncUrl")
    val syncUrl: String? = null,
    @SerialName("channelId")
    val channelId: String? = null,
    @SerialName("liveEdgeDelay")
    val liveEdgeDelay: Double? = null,
)

@Serializable
data class OptaFootball(
    @SerialName("type")
    val type: String? = null,
    @SerialName("optaId")
    val optaId: String? = null,
    @SerialName("fixtureId")
    val fixtureId: String? = null,
    @SerialName("competitionId")
    val competitionId: String? = null,
    @SerialName("tournamentCalendarId")
    val tournamentCalendarId: String? = null,
    @SerialName("competition")
    val competition: String? = null,
    @SerialName("season")
    val season: String? = null,
    @SerialName("match")
    val match: String? = null,
)

@Serializable
data class Advertisement(
    @SerialName("type")
    val type: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("position")
    val position: String? = null,
    @SerialName("isPortrait")
    val isPortrait: String? = null,
    @SerialName("isClickable")
    val isClickable: String? = null,
    @SerialName("deepLinkUrl")
    val deepLinkUrl: String? = null,
    @SerialName("webViewUrl")
    val webViewUrl: String? = null,
    @SerialName("timeToShow")
    val timeToShow: Double? = null,
    @SerialName("ratio")
    val ratio: String? = null,
)

@Serializable
data class Experience(
    @SerialName("type")
    val type: String? = null,
)

@Serializable
data class ExpManifestExtFootball(
    @SerialName("type")
    val type: String? = null,
    @SerialName("periodId")
    val periodId: Double? = null,
    @SerialName("optaFixtureInfo")
    val optaFixtureInfo: OptaFixtureInfo? = null,
)

@Serializable
data class OptaFixtureInfo(
    @SerialName("fixtureId")
    val fixtureId: String? = null,
)