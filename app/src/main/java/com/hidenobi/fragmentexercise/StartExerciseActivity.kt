package com.hidenobi.fragmentexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hidenobi.fragmentexercise.databinding.ActivityStartExerciseBinding

class StartExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBehaviour()
    }

    private fun initBehaviour() {
        binding.startExerciseRunBtn.setOnClickListener { setExercise(Code.TYPE_RUN) }
        binding.startExerciseWalkBtn.setOnClickListener { setExercise(Code.TYPE_WALK) }
    }

    private fun setExercise(type: Int) {
        val intent = Intent(this, SetTimeActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(Code.EXERCISE_TYPE, type)
        intent.putExtra(Code.EXERCISE, bundle)
        startActivity(intent)
    }
}