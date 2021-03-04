package com.example.androiddevchallenge.ui.timer

sealed class CountDownState(val remainingTimeInSeconds: Int) {
    data class Initial(private val time: Int) : CountDownState(time)
    data class Paused(private val time: Int) : CountDownState(time)
    data class InProgress(private val time: Int) : CountDownState(time)
    data class Completed(private val time: Int) : CountDownState(time)
}