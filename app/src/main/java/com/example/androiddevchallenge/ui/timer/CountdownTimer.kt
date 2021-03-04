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
package com.example.androiddevchallenge.ui.timer

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountdownTimer(
    private val initialTime: Int = 5,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val _time = MutableStateFlow<CountDownState>(CountDownState.Initial(initialTime))
    val time: StateFlow<CountDownState> = _time

    private var job: Job? = null

    fun start() {
        runTimer()
    }

    fun pause() {
        job?.cancel()
        CountDownState.Paused(time.value.remainingTimeInSeconds).emit()
    }

    fun resume() {
        CountDownState.InProgress(time.value.remainingTimeInSeconds).emit()
        runTimer()
    }

    fun restart() {
        job?.cancel()
        CountDownState.InProgress(initialTime).emit()
        start()
    }

    private fun runTimer() {
        job = coroutineScope.launch {
            while (time.value.remainingTimeInSeconds > 0) {
                delay(1_000L)
                CountDownState.InProgress(time.value.remainingTimeInSeconds - 1).emit()
            }
            CountDownState.Completed(0).emit()
        }
    }

    private fun CountDownState.emit() {
        val state = this
        Log.d("CountDownState", "emit: $this")
        coroutineScope.launch { _time.emit(state) }
    }
}
