package com.hidenobi.fragmentexercise

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hidenobi.fragmentexercise.databinding.FragmentRunBinding
import com.hidenobi.fragmentexercise.databinding.FragmentWalkBinding
import kotlin.concurrent.timer

class WalkFragment : Fragment() {
    private lateinit var binding: FragmentWalkBinding
    private lateinit var timeLeft: TextView
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalkBinding.inflate(inflater,container,false)
        initComponent()
        val time = arguments!!.getInt("time")
        object : CountDownTimer(time.toLong()*1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.text = "seconds remaining: " + millisUntilFinished/1000
            }
            override fun onFinish() {
                timeLeft.text = "done!"
            }
        }.start()
        return binding.root
    }

    private fun initComponent() {
        timeLeft = binding.txtTimeWalk

    }

}