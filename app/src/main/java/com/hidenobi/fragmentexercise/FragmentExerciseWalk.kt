package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseWalkBinding


class FragmentExerciseWalk : Fragment() {

    private lateinit var view: View
    private var _binding: FragmentExerciseWalkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExerciseWalkBinding.inflate(inflater, container, false)
        view = binding.root
        return view
    }

}