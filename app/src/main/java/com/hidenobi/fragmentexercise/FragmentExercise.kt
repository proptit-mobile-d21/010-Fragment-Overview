package com.hidenobi.fragmentexercise

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding


class FragmentExercise : Fragment() {

    private lateinit var view: View
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!



    private var min: Long = 0
    private var sec: Long = 0
    private var remainingTimeMillis: Long = 0
    private var secondSum : Long = 0
    private lateinit var countDownTimer: CountDownTimer
    private var mIsPause : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        view = binding.root

        return view
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("data"){ requestKey, bundle ->
            val result = bundle.getString("key")
            binding.maintext.text = result
            if(result=="Run"){
                binding.root.setBackgroundResource(R.color.bg_run)
            } else{
                binding.root.setBackgroundResource(R.color.bg_walk)

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("time_left") { requestKey, bundle ->
            min = bundle.getLong("hour") * 60 + bundle.getLong("min")
            binding.txtTimeleft.text = String.format("%02d:%02d", min, sec)
            binding.txtTimecount.text = String.format("%02d:%02d", min, sec)
            secondSum = min * 60 + sec
            remainingTimeMillis = secondSum * 1000
            binding.circularbar.progress = 0
            startTimer()

        }
        binding.apply {
            binding.btnPause.setOnClickListener{
                if(mIsPause){
                    resumeTimer()
                    mIsPause = false
                }
                else{
                    pauseTimer()
                    mIsPause = true
                }
            }


        }
    }

    private fun startTimer(){
        countDownTimer = object : CountDownTimer(remainingTimeMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val minutesRemaining = millisUntilFinished / 1000 / 60
                val secondsRemaining = (millisUntilFinished / 1000) % 60
                remainingTimeMillis = millisUntilFinished
                binding.apply {
                    binding.txtTimeleft.text = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.txtTimecount.text = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                    binding.circularbar.progress = ((millisUntilFinished.toFloat()/(secondSum * 1000).toFloat()) * 100).toInt()
                    binding.horizontalbar.progress = ((millisUntilFinished.toFloat()/(secondSum * 1000).toFloat()) * 100).toInt()
                }

            }

            override fun onFinish() {
                Toast.makeText(context, "Timer end", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun pauseTimer(){
        countDownTimer.cancel()
    }

    private fun resumeTimer(){
        startTimer()
    }


}