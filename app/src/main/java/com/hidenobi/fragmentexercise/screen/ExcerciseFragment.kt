package com.hidenobi.fragmentexercise.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExcerciseBinding
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding
import com.hidenobi.fragmentexercise.modelView.FragmentViewModel
import com.hidenobi.fragmentexercise.section.Time
import java.time.LocalTime
import java.util.Locale

class ExcerciseFragment : Fragment() {
    private lateinit var binding: FragmentExcerciseBinding
    private lateinit var tvTimeLeft: TextView
    private lateinit var tvRorW: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTimePassed: TextView
    private lateinit var timeDuration: LocalTime
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
                val minsPassed = timeDuration.minute.minus(mins)-1
                val secsPassed = 60 - secs-1
                val timePassed = String.format("%02d:%02d", minsPassed, secsPassed)
                tvTimePassed.text = timePassed
            }

            override fun onFinish() {
                tvTimeLeft.text = "Done"
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

//        viewModel.getStartTime.observe(viewLifecycleOwner) {
//            startTime = it
//        }
        startTime = viewModel.getStartTime
        endTime = viewModel.getEndTime
        timeDuration = Time.minusTime(LocalTime.parse(startTime), LocalTime.parse(endTime))
        Log.d("Type", viewModel.getExcerciseType.toString())
        if (viewModel.getExcerciseType == 2) {
            binding.root.background = resources.getDrawable(R.drawable.run_background)
            tvRorW.text = resources.getString(R.string.run)
        } else {
            binding.root.background = resources.getDrawable(R.drawable.walk_background)
            tvRorW.text = resources.getString(R.string.walk)
        }
    }


}