package dev.sunnat629.kmm2.android

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
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
    val timeDisplay = viewModel.timeDisplay.collectAsState()
    val manifestDisplay = viewModel.manifestDisplay.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = timeDisplay.value, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.startFetching() },
                modifier = Modifier
                    .padding(8.dp),
                colors = buttonColors(containerColor = Color(0xFF3DDC84), contentColor = Color.White,)
            ) {
                Text("Start Fetching", color = Color.White)
            }

            Button(
                onClick = { viewModel.stopFetching() },
                modifier = Modifier
                    .padding(8.dp),
                colors = buttonColors(containerColor = Color.Red,contentColor = Color.White,)
            ) {
                Text("Stop Fetching", color = Color.White)
            }
        }
        Spacer(Modifier.heightIn(20.dp))

        Text(text = manifestDisplay.value, modifier = Modifier.padding(bottom = 8.dp))

        Button(
            onClick = { viewModel.startFetchingManifest() },
            modifier = Modifier
                .padding(8.dp),
            colors = buttonColors(containerColor = Color(0xFF3DDC84),contentColor = Color.White,)
        ) {
            Text("Fetch Manifest", color = Color.White)
        }
    }
}