package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.fragment.ExerciseFragment
import com.hidenobi.fragmentexercise.fragment.SetTimeFragment
import com.hidenobi.fragmentexercise.fragment.StartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val startFragment = StartFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainLayout.id, startFragment)
            .commit()
    }
}