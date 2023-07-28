package com.hidenobi.fragmentexercise.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.utils.FormatTime

class ExerciseFragment : Fragment() {
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var exercise: Exercise
    private var timeLeft: Long = 0
    private var countDownTime: Long = 0
    private val currentTime get() = countDownTime - timeLeft
    private val percentProcess get() = currentTime.toDouble() / countDownTime.toDouble() * 100
    private var isCounting: Boolean = true
    private lateinit var countDownTimer: CountDownTimer

    companion object {
        fun newInstance(exercise: Exercise, countDownTime: Long): Fragment {
            return ExerciseFragment().apply {
                this.exercise = exercise
                this.countDownTime = countDownTime
                this.timeLeft = countDownTime
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (exercise) {
            Exercise.RUN -> {
                binding.fragmentExercise.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.orange_gradient, null)
                binding.mode.text = getString(R.string.run)
            }

            Exercise.WALK -> {
                binding.fragmentExercise.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.blue_gradient, null)
                binding.mode.text = getString(R.string.walk)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateUI()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        pauseTimer()
        _binding = null
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateUI()
            }

            override fun onFinish() {
                timeLeft = 0
                Toast.makeText(context, "Chúc mừng bạn đã hoàn thành bài tập", Toast.LENGTH_SHORT)
                    .show()
            }
        }.start()
        isCounting = true
    }

    private fun updateUI() {
        binding.timeLeftText.text =
            "${getString(R.string.time_left)} - ${FormatTime.millisToHourMinuteAndSecond(timeLeft)}"
        binding.timeCount.text = FormatTime.millisToHourMinuteAndSecond(currentTime)
        binding.progressBar.progress = percentProcess.toInt()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        isCounting = false
    }
}