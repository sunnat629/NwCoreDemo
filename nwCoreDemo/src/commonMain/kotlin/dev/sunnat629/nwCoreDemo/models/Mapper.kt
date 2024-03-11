/**
 * @author  S M Mohi Us Sunnat
 * @date    27.10.22
 * Copyright ©2022 NativeWaves GmbH. All rights reserved.
 */

package dev.sunnat629.nwCoreDemo.models

interface TrackMapper<B, G> {
    fun subToBase(track: B, globalTrackId: String?): G
}


class AudioToGlobalTrackMapper : TrackMapper<ExpManifestAudioTrack, ExpManifestGlobalAudioTrack> {
    override fun subToBase(track: ExpManifestAudioTrack, globalTrackId: String?): ExpManifestGlobalAudioTrack {
        return ExpManifestGlobalAudioTrack(
            id = track.id,
            globalAudioTrackId = globalTrackId,
            name = track.name,
            unsynced = track.unsynced,
            audioObject = track.audios,
            unavailabilities = track.unavailabilities,
        )
    }
}

class VideoToGlobalTrackMapper : TrackMapper<ExpManifestVideoTrack, ExpManifestGlobalAudioTrack> {
    override fun subToBase(track: ExpManifestVideoTrack, globalTrackId: String?): ExpManifestGlobalAudioTrack {
        return ExpManifestGlobalAudioTrack(
            id = track.id,
            globalAudioTrackId = globalTrackId,
            name = track.name,
            unsynced = track.unsynced,
            audioObject = track.videos,
            unavailabilities = track.unavailabilities,
        )
    }
}