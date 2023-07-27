package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.model.Time
import com.hidenobi.fragmentexercise.screen.fragment.ExerciseFragment
import com.hidenobi.fragmentexercise.screen.fragment.FragmentStack
import com.hidenobi.fragmentexercise.screen.fragment.SetTimeFragment
import com.hidenobi.fragmentexercise.screen.fragment.StartExerciseFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val exercise: Exercise = Exercise()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Transparent status and navigation bar, fullscreen background
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(
                binding.root.id,
                StartExerciseFragment.newInstance { startExerciseOnClick(it) }
            )
            setReorderingAllowed(true)
            addToBackStack(FragmentStack.BEGIN)
        }
    }

    private fun startExerciseOnClick(type: Exercise.Type) {
        exercise.type = type
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(
                binding.root.id,
                SetTimeFragment.newInstance{ startTime, endTime -> setTimeOnFinish(startTime, endTime)}
            )
            setReorderingAllowed(true)
            addToBackStack(FragmentStack.START_EXERCISE)
        }
    }

    private fun setTimeOnFinish(startTime: Time, endTime: Time) {
        exercise.startTime = startTime
        exercise.endTime = endTime
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(
                binding.root.id,
                ExerciseFragment.newInstance(exercise)
            )
            setReorderingAllowed(true)
            addToBackStack(FragmentStack.SET_TIME)
        }
    }
}