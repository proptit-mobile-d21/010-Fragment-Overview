package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class StartExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_exercise)

//        if (savedInstanceState == null) {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragment_container, StartExerciseFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, StartExerciseFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

}