package dev.sunnat629.kmm2.android

import Utils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sunnat629.kmm2.ExpManifestFetcher
import dev.sunnat629.kmm2.TimelineFetcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _timeDisplay = MutableStateFlow("Nw Timeline: Loading...")
    val timeDisplay: StateFlow<String> = _timeDisplay

    private val _manifestDisplay = MutableStateFlow("EXP Name: Loading...")
    val manifestDisplay: StateFlow<String> = _manifestDisplay

    fun startFetching() {
        viewModelScope.launch {
            TimelineFetcher.startFetchingTimeline {
                _timeDisplay.value = Utils.timestampToHumanReadable(it?.time)
            }
        }
    }

    fun stopFetching() {
        viewModelScope.launch {
            _timeDisplay.value = "Stopped Fetching"
            TimelineFetcher.stopFetchingTimeline()
        }
    }

    fun startFetchingManifest() {
        viewModelScope.launch {
            ExpManifestFetcher.fetchedManifest("p78x44cv1rrm23lx") {
                _manifestDisplay.value = "EXP NAME: ${it?.name}"
            }
        }
    }
}