/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.timer.CountDownState
import com.example.androiddevchallenge.ui.timer.CountdownTimer

@Composable
fun CountDownComponent(timer: CountdownTimer) {
    Scaffold(
        topBar = {
//        TopAppBar(title = { Text(text = "The Final Countdown") })
        }
    ) {
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
