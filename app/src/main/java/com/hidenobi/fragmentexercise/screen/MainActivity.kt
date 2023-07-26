package com.hidenobi.fragmentexercise.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.model.Time
import com.hidenobi.fragmentexercise.screen.fragment.SetTimeFragment
import com.hidenobi.fragmentexercise.screen.fragment.StartExerciseFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val exercise: Exercise = Exercise()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(
                binding.root.id,
                StartExerciseFragment.newInstance { startExerciseOnClick(it) }
            )
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun startExerciseOnClick(type: Exercise.Type) {
        exercise.type = type
        supportFragmentManager.commit {
            replace(
                binding.root.id,
                SetTimeFragment.newInstance{ startTime, endTime -> setTimeOnFinish(startTime, endTime)}
            )
            setReorderingAllowed(true)
            addToBackStack("start_exercise")
        }
    }

    private fun setTimeOnFinish(startTime: Time, endTime: Time) {
        exercise.startTime = startTime
        exercise.endTime = endTime
        // For testing only
        Toast.makeText(
            applicationContext,
            "Cooldown: ${Time.timeBetween(startTime, endTime).toMinute()} minutes",
            Toast.LENGTH_SHORT
        ).show()
    }
}