package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hidenobi.fragmentexercise.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = Bundle()
        val setTimeFragment = SetTimeFragment()

        binding.walk.setOnClickListener {
            bundle.putString("action","WALK")
            setTimeFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(binding.activityStart.id, setTimeFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.run.setOnClickListener {
            bundle.putString("action","RUN")
            setTimeFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(binding.activityStart.id, setTimeFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}