package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class FragmentStartExercise : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_exercise, container, false)

         val btn = view.findViewById<TextView>(R.id.btnrun)

         btn.setOnClickListener {
             val nextFr = FragmentSetTime()
             val fm : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
             fm?.replace(com.google.android.material.R.id.container, nextFr)?.commit()
         }
        return view
    }

}