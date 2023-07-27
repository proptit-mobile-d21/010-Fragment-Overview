package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hidenobi.fragmentexercise.databinding.FragmentSetTimeBinding



class FragmentSetTime : Fragment() {

    private lateinit var view: View
    private var _binding: FragmentSetTimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        view = binding.root
        return view

    }

}