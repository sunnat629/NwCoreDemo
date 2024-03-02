package dev.sunnat629.kmm2.android

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sunnat629.kmm2.TimelineFetcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _displayText = MutableStateFlow("Loading...")
    val displayText: StateFlow<String> = _displayText

    fun startFetching() {
        viewModelScope.launch {
            TimelineFetcher.startFetchingTimeline {
                _displayText.value = it
            }
        }
    }

    fun stopFetching() {
        TimelineFetcher.stopFetchingTimeline()
    }
}