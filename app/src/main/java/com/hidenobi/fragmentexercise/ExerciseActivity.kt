package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class ExerciseActivity : AppCompatActivity() {
    private var timeSelected : Int = 0
    private var timeCountDown: CountDownTimer? = null
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

//        val addBtn: ImageButton = findViewById(R.id.btnAdd)
//        addBtn.setOnClickListener {
            setTimeFunction()
//        }
//        val startBtn: Button = findViewById(R.id.btnPlayPause)
//        startBtn.setOnClickListener {
            startTimerSetup()
      //  }

//        val resetBtn:ImageButton = findViewById(R.id.ib_reset)
//        resetBtn.setOnClickListener {
//            resetTime()
//        }

//        val addTimeTv: TextView = findViewById(R.id.tv_addTime)
//        addTimeTv.setOnClickListener {
//            addExtraTime()
//        }
    }


    private fun resetTime() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
            timeProgress = 0
            timeSelected = 0
            pauseOffSet = 0
            timeCountDown = null
           // val startBtn:Button = findViewById(R.id.btnPlayPause)
           // startBtn.text ="Start"
            isStart = true
            val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
            progressBar.progress = 0
            val timeLeftTv: TextView = findViewById(R.id.tvTimeLeft)
            timeLeftTv.text = "0"
        }
    }

    private fun timePause() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
        }
    }

    private fun startTimerSetup() {
        val startBtn: Button = findViewById(R.id.btnPlayPause)
        if (timeSelected > timeProgress) {
            if (isStart) {
                startBtn.text = "Pause"
                startTimer(pauseOffSet)
                isStart = false
            }
            else {
                isStart =true
                startBtn.text = "Resume"
                timePause()
            }
        }
        else {
            Toast.makeText(this,"Enter Time",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer(pauseOffSetL: Long) {
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        progressBar.progress = timeProgress
        timeCountDown = object :CountDownTimer(
            (timeSelected * 1000).toLong() - pauseOffSetL*1000, 1000) {
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = timeSelected.toLong()- p0/1000
                progressBar.progress = timeSelected-timeProgress
                val timeLeftTv:TextView = findViewById(R.id.tvTimeLeft)
                timeLeftTv.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                resetTime()
                Toast.makeText(this@ExerciseActivity,"Times Up!", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }


    private fun setTimeFunction() {
        val timeLeftTv: TextView = findViewById(R.id.tvTimeLeft)
        val btnStart: Button = findViewById(R.id.btnPlayPause)
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        resetTime()
        timeLeftTv.text = "10"
        btnStart.text = "Start"
        timeSelected = "10".toInt()
        progressBar.max = timeSelected
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timeCountDown!=null) {
            timeCountDown?.cancel()
            timeProgress = 0
        }
    }
}