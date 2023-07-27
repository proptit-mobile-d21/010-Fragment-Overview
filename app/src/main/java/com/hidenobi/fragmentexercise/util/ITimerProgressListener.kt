package com.hidenobi.fragmentexercise.util

interface ITimerProgressListener {
    fun onTick(secondsUntilFinished: Int, secondsPassed: Int, progress: Int)
    fun onFinish(totalSeconds: Int)
}