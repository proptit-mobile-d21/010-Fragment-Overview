package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExcerciseBinding.inflate(inflater, container, false)
        initComponent()
//        val time = LocalTime.parse(viewModel.getEndTime())
        tvTimeLeft.text = Time.minusTime(viewModel.getStartTime(), viewModel.getEndTime())
        Log.d("Check", "ok")
        startTime()
        setupClickListener()
        return binding.root
    }

    private fun startTime() {
        val timer = object :CountDownTimer(, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val hours = (millisUntilFinished / 1000) / 3600;
                val mins = (millisUntilFinished / 1000) % 3600;
                val time = String.format(Locale.getDefault(), "%02d:%02d", hours, mins)
                tvTimeLeft.text = time
            }

            override fun onFinish() {
                tvTimeLeft.text = "Done"
            }
        }
        timer.start()
    }

    private fun setupClickListener() {

    }

    private fun initComponent() {
        tvTimeLeft = binding.tvTimeLeft
        tvDate = binding.tvDate
        tvRorW = binding.tvRorW
        tvTimePassed = binding.tvTimePassed
    }


}