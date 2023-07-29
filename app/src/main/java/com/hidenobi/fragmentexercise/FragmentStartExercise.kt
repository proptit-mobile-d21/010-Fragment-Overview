package com.hidenobi.fragmentexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult


class FragmentStartExercise : Fragment() {
    private lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_exercise, container, false)

        val btnRun = view.findViewById<TextView>(R.id.btnrun)
        val requestKey = "data"
        val bundleKey = "key"

        btnRun.setOnClickListener {
            result = "Run"
            setFragmentResult(requestKey, bundleOf(bundleKey to result))
            val nextFr = FragmentSetTime()
            val fm: FragmentTransaction? =
                requireActivity().supportFragmentManager?.beginTransaction()
            fm?.replace(R.id.framelayout1, nextFr)?.addToBackStack("SetTime")?.commit()

        }

        val btnWalk = view.findViewById<TextView>(R.id.btnwalk)
        btnWalk.setOnClickListener {
            result = "Walk"
            setFragmentResult(requestKey, bundleOf(bundleKey to result))
            val nextFr = FragmentSetTime()
            val fm: FragmentTransaction? =
                requireActivity().supportFragmentManager?.beginTransaction()
            fm?.replace(R.id.framelayout1, nextFr)?.addToBackStack("SetTime")?.commit()
        }

        return view
    }

}