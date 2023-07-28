package com.hidenobi.fragmentexercise.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExcerciseBinding
import com.hidenobi.fragmentexercise.modelView.FragmentViewModel
import com.hidenobi.fragmentexercise.section.Time
import java.time.LocalTime

class ExcerciseFragment : Fragment() {
    private lateinit var binding: FragmentExcerciseBinding
    private lateinit var tvTimeLeft: TextView
    private lateinit var tvRorW: TextView
    private lateinit var tvDate: TextView
    private lateinit var btnPause: ImageView
    private lateinit var tvTimePassed: TextView
    private lateinit var timeDuration: LocalTime
    private lateinit var progressBar: ProgressBar
    private var startTime: String = ""
    private var endTime: String = ""
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExcerciseBinding.inflate(inflater, container, false)
        initComponent()
//        val time = LocalTime.parse(viewModel.getEndTime())
        Log.d("Check", "ok")
        startTime()
        setupClickListener()
        return binding.root
    }

    private fun startTime() {

        val timer = object : CountDownTimer(Time.convertToMillisec(timeDuration).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
//
                val mins = millisUntilFinished / 60000;
                val secs = (millisUntilFinished % 60000) / 1000;
                val time = String.format("%02d:%02d", mins, secs)
                tvTimeLeft.text = time
                val minsPassed = timeDuration.minute.minus(mins) - 1
                val secsPassed = 60 - secs
                val timePassed = String.format("%02d:%02d", minsPassed, secsPassed)
                tvTimePassed.text = timePassed
                val currentProgress = timeDuration.toSecondOfDay() - (millisUntilFinished/1000).toInt()
                Log.d("Progress", currentProgress.toString())
                progressBar.progress = currentProgress
                progressBar.max = timeDuration.toSecondOfDay()
            }

            override fun onFinish() {
                tvTimePassed.text = "00:00"
                tvTimeLeft.text = "00:00"
            }
        }
        timer.start()
    }

    private fun setupClickListener() {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initComponent() {
        tvTimeLeft = binding.tvTimeLeft
        tvDate = binding.tvDate
        tvRorW = binding.tvRorW
        tvTimePassed = binding.tvTimePassed
        btnPause = binding.btnPause
        progressBar = binding.pbCountDown
        startTime = viewModel.getStartTime
        endTime = viewModel.getEndTime
        timeDuration = Time.minusTime(LocalTime.parse(startTime), LocalTime.parse(endTime))

        if (viewModel.getExcerciseType == 2) {
            binding.root.background = resources.getDrawable(R.drawable.run_background)
            tvRorW.text = resources.getString(R.string.run)
        } else {
            binding.root.background = resources.getDrawable(R.drawable.walk_background)
            tvRorW.text = resources.getString(R.string.walk)
        }
    }


}