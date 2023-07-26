package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentExcerciseBinding
import com.hidenobi.fragmentexercise.databinding.FragmentStartBinding
import com.hidenobi.fragmentexercise.modelView.FragmentViewModel

class ExcerciseFragment : Fragment() {
    private lateinit var binding: FragmentExcerciseBinding
    private lateinit var tvTimeLeft: TextView
    private lateinit var tvRorW: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTimePassed: TextView
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExcerciseBinding.inflate(inflater, container, false)
        initComponent()
        tvTimeLeft
        setupClickListener()
        return binding.root
    }

    private fun setupClickListener() {

    }

    private fun initComponent() {
        tvTimeLeft = binding.tvTimeLeft
        tvDate = binding.tvDate
        tvRorW = binding.tvRorW
        tvTimePassed = binding.tvTimePassed
    }


}