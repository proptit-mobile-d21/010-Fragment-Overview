package com.hidenobi.fragmentexercise.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentStartExerciseBinding
import java.util.concurrent.TimeUnit


class StartExerciseFragment : Fragment() {

    private lateinit var binding: FragmentStartExerciseBinding
    private lateinit var type: String
    private var countDownTime: Long = 0
    private lateinit var countDownTimer: CountDownTimer

    companion object {
        fun newInstance(type: String, countDownTime: Long) = StartExerciseFragment().apply {
            this.type = type
            this.countDownTime = countDownTime
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == "Walk") {
            binding.startExercise.setBackgroundResource(R.drawable.walk_gradient_background)
            binding.textMode.text = "WALK"
        } else {
            binding.startExercise.setBackgroundResource(R.drawable.run_gradient_background)
            binding.textMode.text = "RUN"
        }
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(countDownTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val elapsedTime = countDownTime - millisUntilFinished
                val elapsedHours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
                val elapsedMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60
                val elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60

                val remainingHours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                val remainingMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val remainingSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                binding.timeCount.text =
                    String.format("%02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds)
                binding.timeLeft.text =
                    String.format(
                        "%02d:%02d:%02d",
                        remainingHours,
                        remainingMinutes,
                        remainingSeconds
                    )
                binding.progressBar.progress = (elapsedTime / countDownTime).toInt() * 100
            }

            override fun onFinish() {
                countDownTime = 0
                Toast.makeText(context, "Congratulations and BRAVO!", Toast.LENGTH_SHORT).show()
            }
        }
        countDownTimer.start()
    }
}