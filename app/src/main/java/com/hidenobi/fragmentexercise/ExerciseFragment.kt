package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.ExerciseBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ExerciseFragment : Fragment(){
    private lateinit var binding: ExerciseBinding
    private lateinit var startTime: Date
    private lateinit var endTime: Date
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = ExerciseBinding.inflate(inflater,container,false)
        val backgroundResource = arguments?.getInt("backgroundResource")
            ?: R.drawable.exercise_walk_background
        binding.exerciseBackground.setBackgroundResource(backgroundResource)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startTimeStr = arguments?.getString("startTime")
        val endTimeStr = arguments?.getString("endTime")
        val timeLeft = binding.timeLeft

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        startTime = sdf.parse(startTimeStr) ?: Date()
        endTime = sdf.parse(endTimeStr) ?:Date()
        val timeDifference = endTime.time - startTime.time
        countDownTimer = object : CountDownTimer(timeDifference, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / (1000*60)).toInt()
                val seconds = (millisUntilFinished / 1000 % 60).toInt()
                val timeLeftText = String.format(Locale.getDefault(), "%02d : %02d",
                minutes, seconds
                )
                timeLeft.text = timeLeftText
            }
            override fun onFinish() {
                Toast.makeText(requireContext(), "Congratulation on completing your exercise",
                Toast.LENGTH_SHORT).show()
                val activity = requireActivity()
                activity.finish()
            }
        }
        countDownTimer.start()
    }
}