package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val startFragment = StartFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, startFragment)
            addToBackStack(null)
            commit()
        }
    }
}