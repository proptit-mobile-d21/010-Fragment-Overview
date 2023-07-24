package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hidenobi.fragmentexercise.databinding.ActivitySetTimeBinding

class SetTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBehaviour()
    }

    private fun initBehaviour() {
        binding.setTimeBackBtn.setOnClickListener { finish() }
    }
}