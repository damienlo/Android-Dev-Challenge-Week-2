package eur.thefinal.countdown.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.timer.CountDownState
import com.example.androiddevchallenge.ui.timer.CountdownTimer

@Composable
fun CountDownComponent(timer: CountdownTimer) {
    Scaffold(topBar = {
//        TopAppBar(title = { Text(text = "The Final Countdown") })
    }) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val timeState = timer.time.collectAsState()
                val minutes = timeState.value.remainingTimeInSeconds / 60
                val seconds = timeState.value.remainingTimeInSeconds % 60
                val displayableTime = "%02d:%02d".format(minutes, seconds)
                Text(
                    text = displayableTime,
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (timeState.value) {
                    is CountDownState.Initial ->
                        TimerControl(text = "Start", onClick = { timer.start() })
                    is CountDownState.InProgress ->
                        TimerControl(text = "Pause", onClick = { timer.pause() })
                    is CountDownState.Paused ->
                        TimerControl(text = "Resume", onClick = { timer.resume() })
                    is CountDownState.Completed ->
                        TimerControl(text = "Restart", onClick = { timer.restart() })
                }
            }
        }
    }
}