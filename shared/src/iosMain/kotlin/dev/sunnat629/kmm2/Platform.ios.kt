package dev.sunnat629.kmm2

import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionRecordPermissionDenied
import platform.AVFAudio.AVAudioSessionRecordPermissionGranted
import platform.AVFAudio.AVAudioSessionRecordPermissionUndetermined
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

typealias PermissionCallback = (Boolean) -> Unit

object Permissions {
    fun askForMicrophonePermission(callback: PermissionCallback) {
        val session = AVAudioSession.sharedInstance()
        when (session.recordPermission()) {
            AVAudioSessionRecordPermissionGranted -> {
                callback(true)
                println("Microphone access already granted.")
            }
            AVAudioSessionRecordPermissionDenied -> {
                callback(false)
                println("Microphone access denied.")
            }
            AVAudioSessionRecordPermissionUndetermined -> {
                session.requestRecordPermission { granted ->
                    callback(granted)
                }
            }
            else -> println("Unknown microphone permission status.")
        }
    }
}

actual fun getPlatform(): Platform = IOSPlatform()