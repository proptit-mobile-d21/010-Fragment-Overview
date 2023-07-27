package com.hidenobi.fragmentexercise.util

import android.os.CountDownTimer
import com.hidenobi.fragmentexercise.model.Time
import kotlin.math.round

class TimerProgress(time: Time) {
    private var countDownTimer: CountDownTimer? = null
    private val countDownInterval: Long = 1000
    private val countDownTime: Long = time.toMinute() * 60 * countDownInterval

    private var lastCountDownTime: Long = countDownTime

    var listener: ITimerProgressListener? = null
    var isPause: Boolean = false

    private fun startNewCountDown() {
        countDownTimer = null
        countDownTimer = object: CountDownTimer(lastCountDownTime, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsUntilFinished: Double = 1.0*millisUntilFinished/countDownInterval
                val secondsPassed: Double = 1.0*(countDownTime-millisUntilFinished)/countDownInterval
                val progress: Double = 100.0*(countDownTime-millisUntilFinished)/countDownTime
                lastCountDownTime = millisUntilFinished
                listener?.onTick(
                    round(secondsUntilFinished).toInt(),
                    round(secondsPassed).toInt(),
                    round(progress).toInt()
                )
            }

            override fun onFinish() {
                listener?.onFinish((countDownTime/countDownInterval).toInt())
            }
        }
    }

    fun start() {
        lastCountDownTime = countDownTime
        startNewCountDown()
        countDownTimer?.start()
    }

    fun resume() {
        startNewCountDown()
        countDownTimer?.start()
    }

    fun pause() {
        countDownTimer?.cancel()
        isPause = true
    }

    fun cancel() {
        countDownTimer?.cancel()
        countDownTimer = null
        lastCountDownTime = countDownTime
        isPause = false
    }
}