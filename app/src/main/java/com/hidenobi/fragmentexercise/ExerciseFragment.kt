package com.hidenobi.fragmentexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hidenobi.fragmentexercise.databinding.FragmentExerciseBinding


class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataText = arguments?.getString("action2")
        binding.textAct.setText(dataText.toString())
        val dataTime = arguments?.getString("action3")
        binding.textTime.setText("Time Left - " + dataTime.toString())
    }
}