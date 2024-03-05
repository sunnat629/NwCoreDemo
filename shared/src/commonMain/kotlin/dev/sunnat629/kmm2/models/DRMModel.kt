package dev.sunnat629.kmm2.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DRMModel(
    @SerialName("mapping")
    val mapping: List<Mapping>? = null
) {
    @Serializable
    data class Mapping(
        @SerialName("type")
        val type: String? = null,
        @SerialName("sourceId")
        val sourceId: String? = null,
        @SerialName("fairplay")
        val fairplay: Fairplay? = null,
        @SerialName("widevine")
        val widevine: Widevine? = null,
    ) {
        @Serializable
        data class Fairplay(
            @SerialName("licenseUrl")
            val licenseUrl: String? = null,
            @SerialName("certificateUrl")
            val certificateUrl: String? = null,
        )

        @Serializable
        data class Widevine(
            @SerialName("licenseUrl")
            val licenseUrl: String? = null,
        )
    }
}