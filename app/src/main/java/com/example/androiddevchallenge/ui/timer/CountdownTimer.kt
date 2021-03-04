package com.example.androiddevchallenge.ui.timer

import android.util.Log
import com.example.androiddevchallenge.ui.timer.CountDownState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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