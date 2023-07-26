package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.model.Exercise
import com.hidenobi.fragmentexercise.model.Time

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
                SetTimeFragment.newInstance{startTime, endTime -> setTimeOnFinish(startTime, endTime)}
            )
            setReorderingAllowed(true)
            addToBackStack("start_exercise")
        }
    }

    private fun setTimeOnFinish(startTime: Time, endTime: Time) {
        // For testing only
        Toast.makeText(
            applicationContext,
            "Cooldown: ${Time.timeBetween(startTime, endTime).toMinute()} minutes",
            Toast.LENGTH_SHORT
        ).show()
    }
}