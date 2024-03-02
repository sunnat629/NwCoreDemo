package dev.sunnat629.kmm2.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimelineScreen(viewModel: MainViewModel) {
    // Assuming displayText is an observable state in ViewModel
    val displayText = viewModel.displayText.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = displayText.value, modifier = Modifier.padding(bottom = 8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.startFetching() },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Start", color = Color.Green)
            }

            Button(
                onClick = { viewModel.stopFetching() },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Stop", color = Color.Red)
            }
        }
    }
}