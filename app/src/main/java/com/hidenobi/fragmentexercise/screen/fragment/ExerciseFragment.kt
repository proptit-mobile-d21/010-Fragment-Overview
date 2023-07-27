package com.hidenobi.fragmentexercise.screen.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.model.Time
import com.hidenobi.fragmentexercise.util.ITimerProgressListener
import com.hidenobi.fragmentexercise.util.TimerProgress
import kotlin.math.round

private const val EXERCISE_PARAM = "exercise"

class ExerciseFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(exercise: Exercise) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    val exerciseJson = Gson().toJson(exercise)
                    putString(EXERCISE_PARAM, exerciseJson)
                }
            }
    }

    private var _binding: FragmentExerciseBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var exercise: Exercise
    private lateinit var timer: TimerProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val exerciseJson: String? = it.getString(EXERCISE_PARAM)
            exercise = Gson().fromJson(exerciseJson, Exercise::class.java)
            timer = TimerProgress(Time.timeBetween(exercise.startTime!!, exercise.endTime!!))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(layoutInflater, container, false)
        initBasedOnType()
        initBehaviour()
        return binding.root
    }

    private fun initBasedOnType() {
        when (exercise.type) {
            null -> {
                Toast.makeText(context, "Có lỗi trong quá trình chọn bài tập", Toast.LENGTH_SHORT)
                    .show()
                parentFragmentManager.popBackStack(
                    FragmentStack.START_EXERCISE,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
            Exercise.Type.Walk -> {
                binding.root.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.walk_background, null)
                binding.exerciseTypeTxt.text = getString(R.string.walk)
            }
            Exercise.Type.Run -> {
                binding.root.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.run_background, null)
                binding.exerciseTypeTxt.text = getString(R.string.run)
            }
        }
    }

    private fun initBehaviour() {
        binding.exerciseLockBtn.setOnClickListener {
            Toast.makeText(context, "Chưa hỗ trợ tính năng này", Toast.LENGTH_SHORT).show()
        }

        binding.exerciseLocationBtn.setOnClickListener {
            Toast.makeText(context, "Chưa hỗ trợ tính năng này", Toast.LENGTH_SHORT).show()
        }

        binding.exercisePauseBtn.setOnClickListener {
            if (!timer.isPause) {
                timer.pause()
                binding.exercisePauseBtn.setImageResource(R.drawable.play_arrow)
            } else {
                timer.resume()
                binding.exercisePauseBtn.setImageResource(R.drawable.pause_icon)
            }
        }

        timer.listener = object : ITimerProgressListener {
            override fun onTick(secondsUntilFinished: Int, secondsPassed: Int, progress: Int) {
                binding.exerciseTimeLeftTxt.text = getString(
                    R.string.time_left,
                    (secondsUntilFinished / 60),
                    (secondsUntilFinished % 60)
                )

                binding.exerciseTimeCountTxt.text = getString(
                    R.string.time_count,
                    (secondsPassed / 60),
                    (secondsPassed % 60)
                )

                binding.exerciseProgressBar.setProgress(progress, true)
            }

            override fun onFinish(totalSeconds: Int) {
                binding.exerciseTimeLeftTxt.text = getString(R.string.time_left, 0, 0)
                binding.exerciseTimeCountTxt.text = getString(
                    R.string.time_count,
                    (totalSeconds / 60),
                    (totalSeconds % 60)
                )
                binding.exerciseProgressBar.setProgress(100, true)

                Toast.makeText(context, "Hết giờ!", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(context, "Hãy bắt đầu lại", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack(
                        FragmentStack.START_EXERCISE,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                }, 5000)
            }
        }
        timer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timer.cancel()
    }
}