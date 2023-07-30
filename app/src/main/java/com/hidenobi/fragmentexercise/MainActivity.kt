package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.fragment.StartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startFragment = StartFragment()
        supportFragmentManager.beginTransaction().replace(binding.main.id, startFragment).commit()
    }
}

