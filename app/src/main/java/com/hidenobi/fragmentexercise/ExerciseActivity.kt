package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.hidenobi.fragmentexercise.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var timeSelected : Int = 0
    private var timeCountDown: CountDownTimer? = null
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true
    private lateinit var binding : ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityExerciseBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val time = intent.getIntExtra("time",100)
//        setTimeFunction(time)
//        startTimerSetup()
//
//        binding.btnPlayPause.setOnClickListener{
//            startTimerSetup()
//        }
    }


//    private fun resetTime() {
//        if (timeCountDown != null) {
//            timeCountDown!!.cancel()
//            timeProgress = 0
//            timeSelected = 0
//            pauseOffSet = 0
//            timeCountDown = null
//           // val startBtn:Button = findViewById(R.id.btnPlayPause)
//           // startBtn.text ="Start"
//            isStart = true
//            binding.pbTimer.progress = 0
//            binding.tvTimeLeft.setText("00:00:00")
//        }
//    }
//
//    private fun timePause() {
//        if (timeCountDown != null) {
//            timeCountDown!!.cancel()
//        }
//    }
//
//    private fun startTimerSetup() {
//        if (timeSelected > timeProgress) {
//            if (isStart) {
//                binding.btnPlayPause.text = "Pause"
//                startTimer(pauseOffSet)
//                isStart = false
//            }
//            else {
//                isStart =true
//                binding.btnPlayPause.text = "Resume"
//                timePause()
//            }
//        }
//        else {
//            Toast.makeText(this,"Enter Time",Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun startTimer(pauseOffSetL: Long) {
//        binding.pbTimer.progress = timeProgress
//        timeCountDown = object :CountDownTimer(
//            (timeSelected * 1000).toLong() - pauseOffSetL*1000, 1000) {
//            override fun onTick(p0: Long) {
//                timeProgress++
//                pauseOffSet = timeSelected.toLong()- p0/1000
//                binding.pbTimer.progress = timeSelected - timeProgress
//                var hour = ((timeSelected - timeProgress) / 3600).toString()
//                var min = (((timeSelected - timeProgress) % 3600) / 60).toString()
//                var sec = ((timeSelected - timeProgress) % 60).toString()
//                if (hour.toInt() < 10) {
//                    hour = "0$hour"
//                }
//                if (min.toInt() < 10) {
//                    min = "0$min"
//                }
//                if (sec.toInt() < 10) {
//                    sec = "0$sec"
//                }
//                binding.tvTimeLeft.text = "$hour:$min:$sec"
//            }
//
//            override fun onFinish() {
//                resetTime()
//                Toast.makeText(this@ExerciseActivity,"Times Up!", Toast.LENGTH_SHORT).show()
//            }
//
//        }.start()
//    }
//
//
//    private fun setTimeFunction(time : Int) {
//        resetTime()
//        val hour = time / 3600
//        val min = (time % 3600) / 60
//        val sec = time % 60
//        binding.tvTimeLeft.text = "$hour:$min:$sec"
//        binding.btnPlayPause.text = "Start"
//        timeSelected = time
//        binding.pbTimer.max = timeSelected
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if(timeCountDown != null) {
//            timeCountDown?.cancel()
//            timeProgress = 0
//        }
//    }
}