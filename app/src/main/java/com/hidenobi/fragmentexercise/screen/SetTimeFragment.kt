package com.hidenobi.fragmentexercise.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hidenobi.fragmentexercise.R
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SetTimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetTimeFragment : Fragment() {
    private lateinit var binding: FragmentSetTimeBinding
    private lateinit var btnBack: ImageView
    private lateinit var btnStart: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetTimeBinding.inflate(inflater, container, false)


        initComponent()
        setupClickListener()

        return binding.root
    }
    private fun initComponent() {
        btnBack = binding.btnBack
        btnStart = binding.btnStart
    }

    private fun setupClickListener() {
        val setTimeFragment = SetTimeFragment()
        val excerciseFragment = ExcerciseFragment()

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnStart.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, excerciseFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}