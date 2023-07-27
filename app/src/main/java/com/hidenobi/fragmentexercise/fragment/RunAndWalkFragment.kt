package com.hidenobi.fragmentexercise.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.DialogTitle
import com.hidenobi.fragmentexercise.Type
import com.hidenobi.fragmentexercise.databinding.FragmentRunBinding

class RunAndWalkFragment : Fragment() {
    private var timeSelected : Int = 0
    private var timeCountDown: CountDownTimer? = null
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true
    private lateinit var binding : FragmentRunBinding
    companion object{
        fun newInstance(time : Int, type : Type) = RunAndWalkFragment().apply {
            arguments = Bundle().apply {
                putInt("time", time)
                putString("type", type.name)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val time = arguments?.getInt("time")
        val type = arguments?.getString("type")
        Toast.makeText(context, "$time", Toast.LENGTH_SHORT).show()

        binding.tvType.text = type
        if (time != null) {
            setTimeFunction(time)
        }
        startTimerSetup()

        binding.btnPlayPause.setOnClickListener{
            startTimerSetup()
        }
    }

        @SuppressLint("SetTextI18n")
        private fun resetTime() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
            timeProgress = 0
            timeSelected = 0
            pauseOffSet = 0
            timeCountDown = null
            isStart = true
            binding.pbTimer.progress = 0
            binding.tvTimeLeft.text = "00:00:00"
        }
    }

    private fun timePause() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startTimerSetup() {
        if (timeSelected > timeProgress) {
            if (isStart) {
                binding.btnPlayPause.text = "Pause"
                startTimer(pauseOffSet)
                isStart = false
            }
            else {
                isStart =true
                binding.btnPlayPause.text = "Resume"
                timePause()
            }
        }
        else {
            Toast.makeText(context,"Enter Time",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer(pauseOffSetL: Long) {
        binding.pbTimer.progress = timeProgress
        timeCountDown = object :CountDownTimer(
            (timeSelected * 1000).toLong() - pauseOffSetL*1000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = timeSelected.toLong()- p0/1000
                binding.pbTimer.progress = timeSelected - timeProgress
                var hour = ((timeSelected - timeProgress) / 3600).toString()
                var min = (((timeSelected - timeProgress) % 3600) / 60).toString()
                var sec = ((timeSelected - timeProgress) % 60).toString()
                if (hour.toInt() < 10) {
                    hour = "0$hour"
                }
                if (min.toInt() < 10) {
                    min = "0$min"
                }
                if (sec.toInt() < 10) {
                    sec = "0$sec"
                }
                binding.tvTimeLeft.text = "$hour:$min:$sec"
            }

            override fun onFinish() {
                resetTime()
                Toast.makeText(context,"Times Up!", Toast.LENGTH_SHORT).show()

            }

        }.start()
    }


    @SuppressLint("SetTextI18n")
    private fun setTimeFunction(time : Int) {
        resetTime()
        val hour = time / 3600
        val min = (time % 3600) / 60
        val sec = time % 60
        binding.tvTimeLeft.text = "$hour:$min:$sec"
        binding.btnPlayPause.text = "Start"
        timeSelected = time
        binding.pbTimer.max = timeSelected
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timeCountDown != null) {
            timeCountDown?.cancel()
            timeProgress = 0
        }
    }
}

