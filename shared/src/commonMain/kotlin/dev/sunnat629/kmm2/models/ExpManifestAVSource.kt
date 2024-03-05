package dev.sunnat629.kmm2.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ExpManifestAVSource(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: ExpManifestSourceType? = null, // enum ExpManifestSourceType
    @SerialName("hls")
    val hls: ExpManifestHlsSource? = null,
    @SerialName("dash")
    val dash: ExpManifestDashSource? = null,
    @SerialName("mp4")
    val mp4: ExpManifestMp4Source? = null,
    @SerialName("hesp")
    val hesp: ExpManifestHespSource? = null,
    @SerialName("whep")
    val whep: ExpManifestWhepSource? = null,
    @SerialName("millicast")
    val millicast: ExpManifestMillicastSource? = null,
    @SerialName("nwThumbnail")
    val nwThumbnail: ExpManifestNwThumbnailSource? = null,
)

@Serializable(with = ExpManifestSourceTypeSerializer::class)
enum class ExpManifestSourceType {
    @SerialName("Hls")
    HLS,
    @SerialName("Dash")
    DASH,
    @SerialName("Mp4")
    MP4,
    @SerialName("Hesp")
    HESP,
    @SerialName("Millicast")
    MILLICAST,
    @SerialName("Whep")
    WHEP,
    @SerialName("NWThumbnail")
    NWTHUMBNAIL
}

@Serializable
data class ExpManifestHlsSource(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestDashSource(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestMillicastSource(
    @SerialName("streamId")
    val streamId: String? = null,
)

@Serializable
data class ExpManifestMp4Source(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestNwThumbnailSource(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestWhepSource(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestHespSource(
    @SerialName("url")
    val url: String? = null,
)

@Serializable
data class ExpManifestUnavailability(
    @SerialName("id")
    val id: String? = null,
    @SerialName("start")
    val start: Double? = null,
    @SerialName("end")
    val end: Double? = null,
)

object ExpManifestSourceTypeSerializer : KSerializer<ExpManifestSourceType> {
    override val descriptor: SerialDescriptor = serialDescriptor<String>()
    override fun serialize(encoder: Encoder, value: ExpManifestSourceType) {
        encoder.encodeString(value.toString().lowercase())
    }
    override fun deserialize(decoder: Decoder): ExpManifestSourceType {
        // Admittedly, this would accept "Error" in addition to "error".
        return ExpManifestSourceType.valueOf(decoder.decodeString().uppercase())
    }
}