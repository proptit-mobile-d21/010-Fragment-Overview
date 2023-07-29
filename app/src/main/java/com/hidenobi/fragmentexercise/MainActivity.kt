package com.hidenobi.fragmentexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hidenobi.fragmentexercise.databinding.ActivityMainBinding
import com.hidenobi.fragmentexercise.fragment.ExerciseFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ExerciseFragment>(R.id.fragmentContainerView)
               // addToBackStack("ExerciseFragment")
            }
        }
//        binding.btnDelete.setOnClickListener{
////            supportFragmentManager.commit{
////                setReorderingAllowed(true)
////                replace<SetTimeFragment>(R.id.fragmentContainerView)
////            }
//            val intent = Intent(this, ExerciseActivity::class.java)
//            startActivity(intent)
//        }




    }
}