package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hidenobi.fragmentexercise.databinding.StartExerciseBinding

class StartExerciseActivity : AppCompatActivity() {

    private lateinit var binding: StartExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chooseRun = binding.chooseRun
        val chooseWalk = binding.chooseWalk
        val bundle = Bundle()
        chooseRun.setOnClickListener {
            val setTimeFragment = SetTimeFragment()
            bundle.putString("choose", "run")
            setTimeFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.start_layout, setTimeFragment)
                .addToBackStack(null)
                .commit()
        }
        chooseWalk.setOnClickListener {
            val setTimeFragment = SetTimeFragment()
            bundle.putString("choose", "walk")
            setTimeFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.start_layout, setTimeFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}